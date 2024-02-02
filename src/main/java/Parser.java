import Exceptions.InvalidInputException;
import Exceptions.LeluException;
import UI.Ui;
import Commands.*;

public class Parser {
    public static Command parse(String message, Ui ui) throws LeluException {
        if (message.equals("bye")) {
            return new ByeCommand();
        } else if (message.equals("list")) {
            return new ListCommand();
        }
        switch (message.split(" ")[0]) {
            case "mark":
                return new MarkCommand();
            case "unmark":
                return new UnmarkCommand();
            case "delete":
                return new DeleteCommand();
            case "todo":
                return new AddToDoCommand();
            case "deadline":
                return new AddDeadlineCommand();
            case "event":
                return new AddEventCommand();
            default:
                throw new InvalidInputException(ui.showInstructions());
        }
    }
}
