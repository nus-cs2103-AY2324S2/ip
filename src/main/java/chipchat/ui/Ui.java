package chipchat.ui;

import chipchat.exception.ChipchatException;

import java.util.Scanner;

/**
 * Represents the UI of the app.
 */
public class Ui {
    private static final String WELCOME_MSG = "Hello! I'm Chipchat \nWhat can I do for you?";
    private static final String EXIT_MSG = "Good bye! Hope to see you again soon!";
    private final StringBuilder outputBuffer;

    public Ui() {
        this.outputBuffer = new StringBuilder();
    }

    public String getOutput() {
        String output = this.outputBuffer.toString();
        this.outputBuffer.setLength(0);
        return output;
    }


    private void appendWithNewLine(String str) {
        this.outputBuffer.append(str).append("\n");
    }

    public void clearBuffer() {
        this.outputBuffer.setLength(0);
    }

    public void showWelcome() {
        this.appendWithNewLine(WELCOME_MSG);
    }

    public void showBye() {
        this.appendWithNewLine(EXIT_MSG);
    }

    public void showLine() {
        this.appendWithNewLine("----------------------------");
    }

    public void showLoadingError() {
        return;
    }

    public void showMsg(String message) {
        this.appendWithNewLine(message);
    }

    public void showErrMsg(ChipchatException exc) {
        this.appendWithNewLine(exc.toString());
    }
}
