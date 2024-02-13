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
import duke.exception.EmptyDescriptionException;
import duke.exception.NoIndexException;
import duke.exception.UnknownCommandException;
import duke.exception.WrongDateFormatException;
import duke.exception.WrongDateOrderingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parses user commands and performs corresponding actions in the Duke application.
 */

public class CommandParser {

    /**
     * Parses user commands and performs corresponding actions in the Duke application.
     */
    public static Action parseCommand(String command, TaskList taskList) throws
            EmptyDescriptionException, NoIndexException, UnknownCommandException,
            WrongDateFormatException, WrongDateOrderingException {
        try {
            String[] words = command.split(" ");

            switch (words[0]) {
            case "find":
                if (words.length > 1) {
                    String keyword = command.substring(5).trim();
                    taskList.matches(keyword);
                } else {
                    System.out.println("Please provide a keyword to search for.");
                }
                return new Match(taskList);
            case "bye":
                taskList.goodBye();
                return new Farewell();
            case "list":
                if (taskList != null) {
                    taskList.displayTasks();
                    MyList myList = new MyList(taskList);
                    return myList;
                } else {
                    System.out.println("Task list is not initialized.");
                    // Handle the case where taskList is null, such as displaying an error message
                    return null;
                }
            case "list2":
                taskList.displayTasks();
                MyList myList = new MyList(taskList);
                return myList;
            case "mark":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.markTask(index);
                    if (taskList.validateIndex(index)) {
                        Task markedTask = taskList.get(index);
                        return new Mark(markedTask);
                    }
                } else {
                    throw new NoIndexException();
                }
            case "unmark":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.unmarkTask(index);
                    if (taskList.validateIndex(index)) {
                        Task unmarkedTask = taskList.get(index);
                        return new Unmark(unmarkedTask);
                    }
                } else {
                    throw new NoIndexException();
                }
            case "todo":
                if (words.length > 1) {
                    String description = command.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    ToDo todo = new ToDo(description);
                    taskList.addTask(todo);
                    return new Echo("Got it. I've added this task:\n  " + todo + "\nNow you have "
                            + taskList.size() + " tasks in the list.");
                } else {
                    throw new EmptyDescriptionException();
                }

            case "delete":
                if (words.length > 1) {
                    int index = Integer.parseInt(words[1]) - 1;
                    Task deletedTask = taskList.deleteTask(index);
                    if (deletedTask != null) {
                        return new Delete(deletedTask);
                    } else {
                        throw new NoIndexException();
                    }
                } else {
                    throw new NoIndexException();
                }
            case "deadline":
                if (words.length > 1) {
                    try {
                        String[] parts = command.split("/by", 2);
                        String description = parts[0].substring(9).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        LocalDate by = LocalDate.parse(parts[1].trim());
                        Deadline deadline = new Deadline(description, by);
                        taskList.addTask(deadline);
                        return new Echo("Got it. I've added this task:\n  " + deadline + "\nNow "
                                + "you have " + taskList.size() + " tasks in the list.");
                    } catch (DateTimeParseException e) {
                        throw new WrongDateFormatException();
                    }
                } else {
                    throw new EmptyDescriptionException();
                }
            case "event":
                if (words.length > 1) {
                    try {
                        String[] parts = command.split("/from", 2);
                        String description = parts[0].substring(6).trim();
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String[] eventDetails = parts[1].split("/to", 2);
                        LocalDate from = LocalDate.parse(eventDetails[0].trim());
                        LocalDate to = LocalDate.parse(eventDetails[1].trim());
                        if (to.isBefore(from)) {
                            throw new WrongDateOrderingException();
                        }
                        Event event = new Event(description, from, to);
                        taskList.addTask(event);
                        return new Echo("Got it. I've added this task:\n  " + event
                                + "\nNow you have " + taskList.size() + " tasks in the list.");
                    } catch (DateTimeParseException e) {
                        throw new WrongDateFormatException();
                    }
                } else {
                    throw new EmptyDescriptionException();
                }
            default:
                throw new UnknownCommandException();
            }
        } catch (Exception e) {
            throw e;

        }
    }
}

