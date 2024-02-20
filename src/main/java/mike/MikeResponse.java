package mike;

/**
 * Wrapper class for Mike response.
 */
public class MikeResponse {
    private final String response;
    private final boolean isExit;

    /**
     * Constructor for MikeResponse.
     */
    MikeResponse(String response) {
        this(response, false);
    }
    /**
     * Constructor for MikeResponse.
     */
    MikeResponse(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }
    /**
     * Getter for isExit.
     * @return True if response is an exit response, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }
    @Override
    public String toString() {
        return response;
    }
}
