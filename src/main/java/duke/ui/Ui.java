package duke.ui;

/**
 * A utility class to temporarily store the results of Duke's responses.
 */
public class Ui {
    private StringBuilder response = new StringBuilder();

    public Ui() {
    }

    /**
     * Clears the response.
     */
    public void clear() {
        this.response = new StringBuilder();
    }

    /**
     * Get the final response in String format
     *
     * @return the final response in String format
     */
    public String getResponse() {
        return String.valueOf(this.response);
    }

    /**
     * Appends a message to the existing response.
     *
     * @param message the message to be appended
     */
    public void appendResponse(String message) {
        this.response.append(message);
    }
}
