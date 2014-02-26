package net.flinkgutt.speedyreedy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Christian
 */
public class TextInput implements Iterator<String> {

    private int currentWord = 0;
    private final String[] wordsArray;

    public TextInput(String input, int numberOfWords) {
        String[] inputArr = input.split(" ");
        int sizeOfWordsArray = inputArr.length / numberOfWords; // TODO Fix CEIL or something.
        if((inputArr.length % numberOfWords) != 0) {
            sizeOfWordsArray++;
        }
        ArrayList<String> arr = new ArrayList<>(sizeOfWordsArray);

        StringBuilder sb = new StringBuilder();
        int teller = 0;
        for (int i = 0; i < inputArr.length; i++) {
            if (teller < numberOfWords) {
                sb.append(inputArr[i]).append(" ");
                teller++;
            }
            if (teller == numberOfWords) {
                teller = 0;
                arr.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if(sb.length() > 0) {
            arr.add(sb.toString());
        }
        wordsArray = arr.toArray(new String[0]);
    }

    public int count() {
        return wordsArray.length;
    }

    @Override
    public boolean hasNext() {
        return currentWord < wordsArray.length;
    }

    @Override
    public String next() {
        if (hasNext()) {
            return wordsArray[currentWord++];
        }
        return "";
    }

    @Override
    public void remove() {
        // Don't need it, no reason to implement it.
    }

}
