package Commands;

import Irwyn.Exceptions.IrwynException;
import Irwyn.Exceptions.TaskException;

public class CommandParser {
    public static Command parse(String input) throws IrwynException {
        String command = input.split(" ")[0];
        switch (command) {
            case "bye":
                return new EndCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new ToDoCommand(input);
            case "deadline":
                return new DeadlineCommand(input);
            case "event":
                return new EventCommand(input);
            case "mark":
                return new MarkCommand(input);
            case "unmark":
                return new UnmarkCommand(input);
            case "delete":
                return new DeleteCommand(input);
            default:
                throw new TaskException();
        }
    }
}