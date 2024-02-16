package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.action.Action;
import duke.action.Delete;
import duke.action.Echo;
import duke.action.Farewell;
import duke.action.Mark;
import duke.action.Match;
import duke.action.MyList;
import duke.action.TaskList;
import duke.action.Unmark;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parses user commands and performs corresponding actions in the Duke application.
 */

public class CommandParser {
    private static final int KEYWORD_START_INDEX = 5;
    private static final int TODO_START_INDEX = 5;
    private static final int DEADLINE_START_INDEX = 9;
    private static final int EVENT_START_INDEX = 6;
    private static final int MAX_SPLIT = 2;

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
                if (words.length > 1) {
                    String keyword = command.substring(KEYWORD_START_INDEX).trim();
                    taskList.matches(keyword);
                    return new Match(keyword, taskList);
                } else {
                    throw new NoWordException();
                }
            case "bye":
                taskList.goodBye();
                return new Farewell();
            case "list":
                if (taskList != null) {
                    //taskList.displayTasks();
                    return new MyList(taskList);
                } else {
                    return new Echo("Task list is not initialized.");
                }
            case "mark":
                if (words.length > 1) {
                    String[] indicesString = command.substring(5).trim().split(" "); // Remove "mark" and split by spaces
                    if (indicesString.length > 0) {
                        int[] indices = new int[indicesString.length];
                        for (int i = 0; i < indicesString.length; i++) {
                            indices[i] = Integer.parseInt(indicesString[i]) - 1;
                        }
                        return new Mark(indices, taskList);
                    } else {
                        throw new NoIndexException();
                    }
                } else {
                    throw new NoIndexException();
                }
            case "unmark":
                if (words.length > 1) {
                    String[] indicesString = command.substring(7).trim().split(" "); // Remove
                    // "mark" and split by spaces
                    if (indicesString.length > 0) {
                        int[] indices = new int[indicesString.length];
                        for (int i = 0; i < indicesString.length; i++) {
                            indices[i] = Integer.parseInt(indicesString[i]) - 1;
                        }
                        return new Unmark(indices, taskList);
                    } else {
                        throw new NoIndexException();
                    }
                } else {
                    throw new NoIndexException();
                }
            case "todo":
                if (words.length > 1) {
                    String description = command.substring(TODO_START_INDEX).trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    ToDo todo = new ToDo(description);
                    if (taskList.contains(todo)) {
                        throw new DuplicateTaskException();
                    } else {
                        taskList.addTask(todo);
                        return new Echo("Got it. I've added this task:\n  " + todo + "\nNow you have "
                                + taskList.size() + " tasks in the list.");
                    }
                } else {
                    throw new EmptyDescriptionException();
                }
            case "delete":
                if (words.length > 1) {
                    String[] indicesString = command.substring(7).trim().split(" "); // Remove
                    // "mark" and split by spaces
                    if (indicesString.length > 0) {
                        int[] indices = new int[indicesString.length];
                        for (int i = 0; i < indicesString.length; i++) {
                            indices[i] = Integer.parseInt(indicesString[i]) - 1;
                        }
                        return new Delete(indices, taskList);
                    } else {
                        throw new NoIndexException();
                    }
                } else {
                    throw new MissingIndexException();
                }
            case "deadline":
                if (words.length > 1) {
                    try {
                        String[] parts = command.split("/by", MAX_SPLIT);
                        String description = parts[0].substring(DEADLINE_START_INDEX).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        LocalDate by = LocalDate.parse(parts[1].trim());
                        Deadline deadline = new Deadline(description, by);
                        if (taskList.contains(deadline)) {
                            throw new DuplicateTaskException();
                        } else {
                            taskList.addTask(deadline);
                            return new Echo("Got it. I've added this task:\n  " + deadline + "\nNow "
                                    + "you have " + taskList.size() + " tasks in the list.");
                        }
                    } catch (DateTimeParseException e) {
                        throw new WrongDateFormatException();
                    }
                } else {
                    throw new EmptyDescriptionException();
                }
            case "event":
                if (words.length > 1 && command.contains("/from") && command.contains("/to")) {
                    try {
                        String[] parts = command.split("/from", MAX_SPLIT);
                        String description = parts[0].substring(EVENT_START_INDEX).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] eventDetails = parts[1].split("/to", MAX_SPLIT);
                        LocalDate from = LocalDate.parse(eventDetails[0].trim());
                        LocalDate to = LocalDate.parse(eventDetails[1].trim());
                        if (to.isBefore(from)) {
                            throw new WrongDateOrderingException();
                        }
                        Event event = new Event(description, from, to);
                        if (taskList.contains(event)) {
                            throw new DuplicateTaskException();
                        } else {
                            taskList.addTask(event);
                            return new Echo("Got it. I've added this task:\n  " + event
                                    + "\nNow you have " + taskList.size() + " tasks in the list.");
                        }
                    } catch (DateTimeParseException e) {
                        throw new WrongDateFormatException();
                    }
                } else {
                    throw new InvalidEventFormatException();
                }

            default:
                throw new UnknownCommandException();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

