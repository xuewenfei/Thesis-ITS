package com.westlyf.controller;

import com.westlyf.domain.exercise.practical.PracticalPrintExercise;
import com.westlyf.utils.RuntimeUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by robertoguazon on 04/09/2016.
 */
public class PracticalPrintExerciseViewerController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML private TextArea instructionsTextArea;

    @FXML private TextArea codeTextArea;
    @FXML private Button clearCodeButton;
    @FXML private Button runCodeButton;

    @FXML private TextArea outputTextArea;
    @FXML private Button clearOutputButton;

    @FXML private Button submitButton;
    @FXML private Label responseText;

    private PracticalPrintExercise practicalPrintExercise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //submitButton.setDisable(true);
    }

    public void setPracticalPrintExercise(PracticalPrintExercise practicalPrintExercise) {
        this.practicalPrintExercise = practicalPrintExercise;

        titleLabel.setText(practicalPrintExercise.getTitle());
        instructionsTextArea.setText(practicalPrintExercise.getInstructions());

        codeTextArea.setText(practicalPrintExercise.getCode());
        practicalPrintExercise.codeProperty().bind(codeTextArea.textProperty());

    }

    public PracticalPrintExercise getPracticalPrintExercise() {
        return practicalPrintExercise;
    }

    @FXML
    private void clearCode() {
        this.codeTextArea.clear();
    }

    @FXML
    private void runCode() {
        if (practicalPrintExercise != null) {
            compileCode();
            if ( RuntimeUtil.STRING_OUTPUT.toString().isEmpty()) {
                outputError("use System.out.println() to output something");
            }
            outputStream(RuntimeUtil.STRING_OUTPUT.toString());
            if (practicalPrintExercise.evaluate(RuntimeUtil.STRING_OUTPUT.toString())){
                if(practicalPrintExercise.checkCGroup(codeTextArea.textProperty())){
                    responseText.setText("Correct");
                } else responseText.setText("Incorrect: Cheating");
            }else {
                responseText.setText("Incorrect Output");
            }
        }
    }

    @FXML
    private void clearOutput() {

        try {
            this.outputTextArea.clear();
            RuntimeUtil.reset(RuntimeUtil.STRING_OUTPUT);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private void submit() {
        //TODO evaluate and get score and push to database
        compileCode();
        if (practicalPrintExercise.evaluate(RuntimeUtil.STRING_OUTPUT.toString())) {
            if(practicalPrintExercise.checkCGroup(codeTextArea.textProperty())){
                System.out.println("Correct: true");
            } else System.out.println("Correct: false, no cheating");
        } else System.out.println("Correct: false");
        System.out.println("output: " + RuntimeUtil.STRING_OUTPUT.toString());
    }

    private void compileCode() {
        try {
            System.out.println("try: ");
            RuntimeUtil.setOutStream(RuntimeUtil.STRING_STREAM);
            RuntimeUtil.reset(RuntimeUtil.STRING_OUTPUT);
            RuntimeUtil.compile(practicalPrintExercise);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            RuntimeUtil.setOutStream(RuntimeUtil.CONSOLE_STREAM);
        }
    }

    private void outputStream(String string) {
        outputTextArea.appendText(string);
    }

    private void outputLine(String string) {
        outputTextArea.appendText(string + "\n");
    }

    private void outputError(String string) {
        outputTextArea.appendText("Error: " + string + "\n");
    }
}
