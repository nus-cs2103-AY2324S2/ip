package main;

/**
 * Represents a categorized response
 * from the main processing logic. It includes a flag indicating whether the
 * response is an error and the actual response message returned from the main logic.
 */
public class MainResponseCategorized {

    /**
     * Indicates whether the response is an error.
     */
    private boolean isError = false;

    /**
     * The response message returned from the main processing logic.
     */
    private final String responseReturnedFromMain;

    /**
     * Constructs a new {@code MainResponseCategorized} instance.
     *
     * @param isError {@code true} if the response is an error, {@code false} otherwise.
     * @param responseReturnedFromMain The response message returned from the main processing logic.
     */
    public MainResponseCategorized(boolean isError, String responseReturnedFromMain) {
        this.isError = isError;
        this.responseReturnedFromMain = responseReturnedFromMain;
    }

    /**
     * Checks whether the response is an error.
     *
     * @return {@code true} if the response is an error, {@code false} otherwise.
     */
    public boolean isMessageAError() {
        return isError;
    }

    /**
     * Gets the response message returned from the main processing logic.
     *
     * @return The response message.
     */
    public String getResponseReturnedFromMain() {
        return this.responseReturnedFromMain;
    }
}
