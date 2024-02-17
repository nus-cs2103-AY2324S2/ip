package Commands;

import Exceptions.DudeException;
import Tasks.TaskList;
import Utils.Storage;

public class CommandParser {

    public static Command parseCommand(String input, TaskList tasklist) throws DudeException {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand(tasklist);
        case "delete":
            return new DeleteCommand(input, tasklist);
        case "todo":
            return new TodoCommand(input, tasklist);
        case "deadline":
            return new DeadlineCommand(input, tasklist);
        case "event":
            return new EventCommand(input, tasklist);
        case "mark":
            return new MarkCommand(input, tasklist);
        case "unmark":
            return new UnmarkCommand(input, tasklist);
        case "help":
            return new HelpCommand(input);
        default:
            return new InvalidCommand();
        }
    }


}
