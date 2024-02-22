package chipchat.ui;

import chipchat.exception.ChipchatException;

/**
 * Represents the UI of the app.
 */
public class Ui {
    private static final String WELCOME_MSG = "Hello! I'm ChipChat. \nWhat can I do for you?";
    private final StringBuilder outputBuffer;

    /**
     * Constructs the ui.
     */
    public Ui() {
        this.outputBuffer = new StringBuilder();
    }

    /**
     * Flushes the content of the output string buffer.
     *
     * @return the output string buffer
     */
    public String getOutput() {
        String output = this.outputBuffer.toString();
        this.clearBuffer();
        return output;
    }

    /**
     * Clears the current string buffer.
     */
    public void clearBuffer() {
        this.outputBuffer.setLength(0);
    }

    /**
     * Appends a welcome message to the output buffer.
     */
    public void showWelcome() {
        this.appendWithNewLine(WELCOME_MSG);
    }

    /**
     * Appends a message to the output buffer.
     *
     * @param message the message to be printed
     */
    public void showMsg(String message) {
        this.appendWithNewLine(message);
    }

    /**
     * Appends an error message to the output buffer.
     *
     * @param exc the exception caught
     */
    public void showErrMsg(ChipchatException exc) {
        this.appendWithNewLine(exc.toString());
    }

    private void appendWithNewLine(String str) {
        this.outputBuffer.append(str).append("\n");
    }

}
