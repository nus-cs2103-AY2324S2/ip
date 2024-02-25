package ken.response;

/**
 * The Response class represents a response to a user command.
 * It contains the message to be displayed and other relevant information.
 */
public class Response {
    private final String message;

    /**
     * Constructs a Response with the specified message.
     *
     * @param message The message to be displayed.
     */
    public Response(String message) {
        this.message = message;
    }


    /**
     * Gets the message of the response.
     *
     * @return The message of the response.
     */
    public String getMessage() {
        return message;
    }
}

