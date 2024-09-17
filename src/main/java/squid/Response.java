package squid;

/**
 * Object to encapsulate a response given by Squid.
 */
public class Response {
    private boolean isLoop;
    private String response;

    /**
     * Constructor for Response object.
     * @param isLoop whether to continue accepting user input.
     * @param response String representation of Squid's response.
     */
    public Response(boolean isLoop, String response) {
        this.isLoop = isLoop;
        this.response = response;
    }

    /**
     * Getter for whether Squid should continue accepting user input.
     * @return whether to continue accepting user input.
     */
    public boolean getIsLoop() {
        return isLoop;
    }

    /**
     * Getter for Squid's response.
     * @return String representation of Squid's response.
     */
    public String getResponse() {
        return response;
    }
}
