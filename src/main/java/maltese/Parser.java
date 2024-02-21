package maltese;


import maltese.action.Action;
import maltese.action.ChangeFilePath;
import maltese.action.Delete;
import maltese.action.Farewell;
import maltese.action.Help;
import maltese.action.Mark;
import maltese.action.Match;
import maltese.action.TaskList;
import maltese.action.Unmark;
import maltese.exception.MalteseException;
import maltese.exception.UnknownCommandException;
import maltese.task.Deadline;
import maltese.task.Event;
import maltese.task.ToDo;

import java.io.IOException;

/**
 * Parses user commands and performs corresponding actions in the maltese application.
 */

public class Parser {


    /**
     * Parses user commands and performs corresponding actions in the maltese application.
     */
    public static Action parseCommand(String command, TaskList taskList, Storage storage) throws MalteseException, IOException {
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
            case "change":
                return ChangeFilePath.parse(command, storage);
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

