public class InvalidIndexException extends CheckbotException {
    public InvalidIndexException(String index) {
        super("\"" + index + "\" is not a number.");
    }

    public InvalidIndexException(int index, int length) {
        super(length == 0
                ? "Please add tasks into your list before doing that!"
                : "Task number " + index
                + " does not exist. "
                + (length > 1
                    ?  "Only task numbers 1 - "
                        + length + " are accepted."
                    : "You only have 1 task in your list."));
    }
}
