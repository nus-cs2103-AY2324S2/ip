package duke;

import java.time.LocalDate;
import java.util.Arrays;

import duke.commands.Command;
import duke.commands.CommandBye;
import duke.commands.CommandDeadline;
import duke.commands.CommandDelete;
import duke.commands.CommandEvent;
import duke.commands.CommandList;
import duke.commands.CommandMark;
import duke.commands.CommandTodo;
import duke.commands.CommandUnknown;
import duke.commands.CommandUnmark;
import duke.exceptions.DukeException;
import duke.exceptions.tasks.EmptyDescriptionException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class Parser {

    private static Command parseCommandList() {
        return new CommandList();
    }

    private static Command parseCommandBye() {
        return new CommandBye();
    }

    private static Command parseCommandMark(String[] args) {
        Integer taskNum = Integer.parseInt(args[1]);
        Integer taskIndex = taskNum - 1;

        return new CommandMark(taskIndex);
    }

    private static Command parseCommandUnmark(String[] args) {
        Integer taskNum = Integer.parseInt(args[1]);
        Integer taskIndex = taskNum - 1;

        return new CommandUnmark(taskIndex);
    }

    private static Command parseCommandTodo(String[] args) throws EmptyDescriptionException {
        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);
        String description = String.join(" ", taskArgs);

        Todo todo = new Todo(description);

        return new CommandTodo(todo);
    }

    private static Command parseCommandDeadline(String[] args) throws EmptyDescriptionException {
        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);
        String byDelim = "/by";

        String argsStr = String.join(" ", taskArgs);
        int byIndex = argsStr.indexOf(byDelim);

        String byStr = argsStr.substring(byIndex + byDelim.length()).trim();
        LocalDate by = LocalDate.parse(byStr);

        String description = argsStr.substring(0, byIndex).trim();

        Deadline deadline = new Deadline(description, by);

        return new CommandDeadline(deadline);
    }

    private static Command parseCommandEvent(String[] args) throws EmptyDescriptionException {
        String[] taskArgs = Arrays.copyOfRange(args, 1, args.length);
        String fromDelim = "/from";
        String toDelim = "/to";

        String argsStr = String.join(" ", taskArgs);
        int fromIndex = argsStr.indexOf(fromDelim);
        int toIndex = argsStr.indexOf(toDelim);

        String fromStr = argsStr.substring(fromIndex + fromDelim.length(), toIndex).trim();
        String toStr = argsStr.substring(toIndex + toDelim.length()).trim();

        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);
        String description = argsStr.substring(0, fromIndex).trim();

        Event event = new Event(description, from, to);

        return new CommandEvent(event);
    }

    private static Command parseCommandDelete(String[] args) {
        Integer taskNum = Integer.parseInt(args[1]);
        Integer taskIndex = taskNum - 1;

        return new CommandDelete(taskIndex);
    }

    private static Command parseCommandUnknown() {
        return new CommandUnknown();
    }

    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.strip();

        String[] args = fullCommand.split(" ");
        String commandType = args[0];

        Command command;
        switch (commandType) {
        case "list":
            command = parseCommandList();
            break;
        case "bye":
            command = parseCommandBye();
            break;
        case "mark":
            command = parseCommandMark(args);
            break;
        case "unmark":
            command = parseCommandUnmark(args);
            break;
        case "todo":
            command = parseCommandTodo(args);
            break;
        case "deadline":
            command = parseCommandDeadline(args);
            break;
        case "event":
            command = parseCommandEvent(args);
            break;
        case "delete":
            command = parseCommandDelete(args);
            break;
        default:
            command = parseCommandUnknown();
            break;
        }

        return command;
    }
}
