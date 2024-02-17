package duke.ui;

/**
 * Represents the response from the bot.
 */
public class Response {
    private String response;

    /**
     * Constructs a new response.
     *
     * @param response The response from the bot.
     */
    public Response(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return response;
    }
}
