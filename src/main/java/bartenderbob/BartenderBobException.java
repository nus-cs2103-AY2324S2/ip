package bartenderbob;

/**
 * Handles exceptions that is specific to BartenderBob chatbot.
 */
public class BartenderBobException extends Exception{
    /** First word of the user input */
    private String firstWord;
    /** Displays messages to the user */
    private static final Ui UI = new Ui();

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
    public static void invalidInput(String userInput) {
        UI.showInvalidInputError(userInput);
    }

    /**
     * Displays error message when a user gives an input
     * with missing parameters that is required by BartenderBob.
     */
    public void displayError() {
        switch (firstWord) {
        case "mark":
            UI.showInvalidMarkCommand();
            break;
        case "unmark":
            UI.showInvalidUnmarkCommand();
            break;
        case "delete":
            UI.showInvalidDeleteCommand();
            break;
        case "todo":
            UI.showInvalidTodoCommand();
            break;
        case "deadline":
            UI.showInvalidDeadlineCommand();
            break;
        case "event":
            UI.showInvalidEventCommand();
            break;
        }
    }

    /**
     * Displays error message when a user specified task index
     * is out of bounds.
     */
    public void tasksOutOfBounds() {
        UI.showOutOfBoundsCommand();
    }
}
