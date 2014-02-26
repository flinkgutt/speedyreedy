package net.flinkgutt.speedyreedy;

import java.util.Iterator;

/**
 *
 * @author Christian
 */
public class TextInput implements Iterator<String> {
    int currentWord = 0;
    String[] wordsArray;
    public TextInput(String input) {
        wordsArray = input.split(" ");
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
        if(hasNext()) {
            return wordsArray[currentWord++];
        }
        return "";
    }

    @Override
    public void remove() {
        // Don't need it, no reason to implement it.
    }
    
}
