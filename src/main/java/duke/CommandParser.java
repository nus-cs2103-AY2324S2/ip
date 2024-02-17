package duke;


import duke.action.*;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parses user commands and performs corresponding actions in the Duke application.
 */

public class CommandParser {

    /**
     * Parses user commands and performs corresponding actions in the Duke application.
     */
    public static Action parseCommand(String command, TaskList taskList) throws DukeException {
        try {
            String[] words = command.split(" ");

            // Convert the first word to lowercase for case-insensitive comparison
            String commandWord = words[0].toLowerCase();

            switch (commandWord) {
            case "find":
                return Match.parse(command, taskList);
            case "bye":
                return Farewell.parse(taskList);
            case "help":
                return Help.parse();
            case "list":
                return TaskList.parse(taskList);
            case "mark":
                return Mark.parse(command, taskList);
            case "unmark":
                return Unmark.parse(command, taskList);
            case "todo":
                return ToDo.parse(command, taskList);
            case "delete":
                return Delete.parse(command, taskList);
            case "deadline":
                return Deadline.parse(command, taskList);
            case "event":
                return Event.parse(command, taskList);

            default:
                throw new UnknownCommandException();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

