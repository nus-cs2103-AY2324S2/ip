package bartenderbob;
public class BartenderBobException extends Exception{
    private String firstWord;
    private static final Ui UI = new Ui();
    public BartenderBobException() {
    }
    public BartenderBobException(String firstWord) {
        this.firstWord = firstWord;
    }
    public static void invalidInput(String userInput) {
        UI.showInvalidInputError(userInput);
    }
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
    public void tasksOutOfBounds() {
        UI.showOutOfBoundsCommand();
    }
}
