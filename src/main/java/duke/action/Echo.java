package duke.action;

/**
 * Represents an action to echo a response message.
 */

public class Echo implements Action {

    /**
     * The content of the response message.
     */
    private final String stuff;

    /**
     * Constructs an Echo action with the specified content for the response message.
     *
     * @param stuff The content of the response message.
     */
    public Echo(String stuff) {
        this.stuff = stuff;
    }

    /**
     * Gets the response message with the added content.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        return stuff;
    }
}

