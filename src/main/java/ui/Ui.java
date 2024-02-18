package ui;

/**
 * Responsible for interacting with the user through the command line interface.
 */
public class Ui {
    private StringBuilder output = new StringBuilder();

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        output.append("Hello! I'm TodoPal!\nWhat can I do for you?");
    }

    /**
     * Returns the Ui response and resets the output String.
     *
     * @return String that is passed into GUI.
     */
    public String getOutput() {
        String temp = output.toString();
        assert !temp.equals("") : "Output should not be empty";
        output = new StringBuilder();
        return temp;
    }
}
