package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.command.WrongCommand;

public class Parser {
    public static Command parse(String message) {
        String firstWord = message.split(" ")[0].toLowerCase();
        switch (firstWord) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(message);
            case "unmark":
                return new UnmarkCommand(message);
            case "delete":
                return new DeleteCommand(message);
            case "todo":
                return new ToDoCommand(message);
            case "deadline":
                return new DeadlineCommand(message);
            case "event":
                return new EventCommand(message);
            case "bye":
                return new ExitCommand();
            default:
                return new WrongCommand(message);
        }
    }
}
