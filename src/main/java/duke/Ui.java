package duke;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Handles user interface interactions for the Duke application.
 * It displays welcome messages, prompts for user input, and shows results to the user.
 */
public class Ui {
    private final BufferedReader br;
    private String response = "";

    /**
     * Constructs a UI instance with the specified input stream.
     *
     * @param in The input stream for user input.
     */
    public Ui(InputStream in) {
        this.br = new BufferedReader(new InputStreamReader(in));
    }

    public String getResponse() {
        return this.response;
    }

    /**
     * Displays the welcome message when the Duke application starts.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Hao Wen\n" + "What can I do for you?");
    }

    /**
     * Prompts the user to enter a command and reads the user's input.
     *
     * @return The user's input command as a string.
     */
    public String getUserRequest() {
        try {
            System.out.println("Enter a command:");
            return br.readLine();
        } catch (Exception e) {
            System.out.println("Error:");
            return "";
        }
    }

    public void clear() {
        this.response = "";
    }

    public void addResponse(String message) {
        this.response += message;
    }

    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays the provided string message to the user.
     *
     */
    public void showResultToUser() {
        System.out.println(this.response);
    }
}
