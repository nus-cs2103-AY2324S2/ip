package duke.ui;

/**
 * The Ui class is responsible for handling user interface interactions
 * in the Duke application, including reading user input and displaying messages.
 */
public class Ui {
    private StringBuilder responses;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.responses = new StringBuilder();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String welcomeMessage = "Hello! I'm EchoPilot.\nWhat can I do for you?";
        collectResponse(welcomeMessage);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        collectResponse(goodbyeMessage);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        collectResponse(message);
    }

    /**
     * Displays an error message indicating failure to load tasks from a file.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        collectResponse(message);
    }

    /**
     * Clears the accumulated responses, preparing for the next set of messages.
     */
    public void clearResponse() {
        responses.setLength(0);
    }

    /**
     * Adds a response message to the responses StringBuilder.
     *
     * @param response The response message to be added.
     */
    public void showResponse(String response) {
        responses.append(response).append("\n");
    }

    /**
     * Gets the accumulated response messages as a single string.
     *
     * @return The concatenated response messages.
     */
    public String getResponse() {
        String response = responses.toString();
        responses.setLength(0);
        return response;
    }

    /**
     * Adds a message to the responses StringBuilder, ensuring it's formatted for display.
     *
     * @param message The message to be added.
     */
    public void collectResponse(String message) {
        if (responses.length() > 0) {
            responses.append("\n\n");
        }
        responses.append(message);
    }
}
