package net.flinkgutt.speedyreedy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button inputTextButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button startButton;
    
    @FXML
    private Button continueButton;

    @FXML
    private Label textLabel;

    @FXML
    private TextField wpmField;
    
    @FXML
    private TextArea textInput;
    
    private Timeline wpmTimeLine;

    @FXML
    void handleInputTextButtonAction(ActionEvent event) {
        
    }

    @FXML
    void handlePauseButtonAction(ActionEvent event) {
        if (wpmTimeLine != null) {
            wpmTimeLine.pause();
            continueButton.setDisable(false);
        }
    }

    @FXML
    void handleContinueButtonAction(ActionEvent event) {
        if (wpmTimeLine != null) {
            wpmTimeLine.play();
            continueButton.setDisable(true);
        }
    }

    @FXML
    void handleStartButtonAction(ActionEvent event) {
        double wpm = Double.parseDouble(wpmField.getText());
        double pause = 1000 / (wpm / 60);
        
        final TextInput words = new TextInput(textInput.getText());
        wpmTimeLine = new Timeline(new KeyFrame(Duration.millis(pause), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textLabel.setText(words.next());
            }
        }));
        wpmTimeLine.setCycleCount(words.count());
        pauseButton.setDisable(false);
        wpmTimeLine.play();
        wpmTimeLine.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                pauseButton.setDisable(true);
                continueButton.setDisable(true);
            }
        });
    }

    @FXML
    void initialize() {
        assert inputTextButton != null : "fx:id=\"inputTextButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert pauseButton != null : "fx:id=\"pauseButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert startButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert textLabel != null : "fx:id=\"textLabel\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert continueButton != null : "fx:id=\"continueButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert textInput != null : "fx:id=\"textInput\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert wpmField != null : "fx:id=\"wpmField\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
