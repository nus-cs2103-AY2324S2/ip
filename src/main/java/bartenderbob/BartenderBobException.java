package bartenderbob;

/**
 * Handles exceptions that is specific to BartenderBob chatbot.
 */
public class BartenderBobException extends Exception {
    /** Displays messages to the user */
    private static final Ui UI = new Ui();
    /** First word of the user input */
    private String firstWord;
    /**
     * Creates an instance of BartenderBobException.
     */
    public BartenderBobException() {
    }

    /**
     * Creates an instance of BartenderBobException and initialise
     * it with the first word of the user input.
     *
     * @param firstWord First word of the user input.
     */
    public BartenderBobException(String firstWord) {
        this.firstWord = firstWord;
    }

    /**
     * Shows invalid input error message when a user
     * types in a command BartenderBob cannot comprehend.
     *
     * @param userInput The user input to BartenderBob.
     */
    public static String invalidInput(String userInput) {
        assert userInput != null : "user input cannot be null";
        return UI.showInvalidInputError(userInput);
    }

    /**
     * Displays error message when a user gives an input
     * with missing parameters that is required by BartenderBob.
     */
    public String displayError() {
        assert firstWord != null : "first word cannot be null";
        switch (firstWord) {
        case "mark":
            return UI.showInvalidMarkCommand();
        case "unmark":
            return UI.showInvalidUnmarkCommand();
        case "delete":
            return UI.showInvalidDeleteCommand();
        case "todo":
            return UI.showInvalidTodoCommand();
        case "deadline":
            return UI.showInvalidDeadlineCommand();
        case "event":
            return UI.showInvalidEventCommand();
        default:
            return "";
        }
    }

    /**
     * Displays error message when a user specified task index
     * is out of bounds.
     */
    public String tasksOutOfBounds() {
        return UI.showOutOfBoundsCommand();
    }
    public static String showEventClashError(Event newEvent, Event existingEvent) {
        return UI.showClashError(newEvent, existingEvent);
    }
}
