package maltese.action;

/**
 * Represents an action to echo a response message.
 */

public class Echo implements Action {

    /**
     * The content of the response message.
     */
    private final String word;

    /**
     * Constructs an Echo action with the specified content for the response message.
     *
     * @param word The content of the response message.
     */
    public Echo(String word) {
        this.word = word;
    }

    /**
     * Gets the response message with the added content.
     *
     * @return A string representing the response message.
     */
    @Override
    public String getResponse() {
        return word;
    }
}

