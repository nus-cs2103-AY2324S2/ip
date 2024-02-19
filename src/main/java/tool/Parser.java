package tool;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exception.ChronosException;
import task.Task;

/**
 * Represents the tool to process user commands.
 */
public class Parser {
    /**
     * Constructs a Parser object.
     */
    private Parser() {
    }

    /**
     * Changes the format the date specified for a deadline or an event.
     *
     * @param dateTime Date and time of a deadline or an event in the format yyyy-mm-dd HH:MM.
     * @return Formatted date and time.
     */
    public static String formatDateTime(String dateTime) {
        String[] dateTimeArray = dateTime.split(" ");
        String date = dateTimeArray[0];
        String time = dateTimeArray[1];
        String combinedDateTime = date + "T" + time;
        return LocalDateTime.parse(combinedDateTime).format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    /**
     * Processes the command provided by the user.
     *
     * @param command Command provided by the user.
     * @param ui Ui object that deals with the interactions with the user.
     * @param storage Storage object that deals with loading tasks from file and saving tasks to file.
     * @param tasks TaskList object that contains the task list.
     * @return Integer to indicate whether program should terminate.
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String processCommand(
            String command, Ui ui, Storage storage, TaskList tasks) throws IOException, ChronosException {

        String[] token = command.split(" ", 2);

        if (token[0].equals("bye")) {
            try {
                if (token.length != 1) {
                    throw ChronosException.createInvalidByeException();
                } else {
                    return Ui.bidGoodbye();
                }
            } catch (exception.InvalidByeException e) {
                return e.getMessage();
            }
            // Fallthrough
        } else {
            switch(token[0]) {
            case "help":
                try {
                    if (token.length != 1) {
                        throw ChronosException.createInvalidHelpException();
                    } else {
                        return ui.printHelp();
                    }
                } catch (exception.InvalidHelpException e) {
                    return e.getMessage();
                }
                // Fallthrough
            case "list":
                try {
                    if (token.length != 1) {
                        throw ChronosException.createInvalidListException();
                    } else if (tasks.isEmpty()) {
                        return ui.printNoOutstandingTasks();
                    } else {
                        return ui.printTasks(tasks);
                    }
                } catch (exception.InvalidListException e) {
                    return e.getMessage();
                }
                // Fallthrough
            case "mark":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingTaskNumberException();
                    } else {
                        try {
                            int i = Integer.parseInt(token[1]);
                            Task selectedTaskToBeMarked = tasks.markTask(i, ui);
                            storage.saveTasksToFile(tasks);
                            return ui.printMarkTaskSuccessful(selectedTaskToBeMarked);
                        } catch (NumberFormatException e) {
                            return ui.printNumberFormatException();
                        } catch (IndexOutOfBoundsException e) {
                            return ui.printIndexOutOfBoundsException();
                        }
                    }
                } catch (exception.MissingTaskNumberException e) {
                    return e.getMessage();
                }
                // Fallthrough
            case "unmark":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingTaskNumberException();
                    } else {
                        try {
                            int i = Integer.parseInt(token[1]);
                            Task selectedTaskToBeUnmarked = tasks.unMarkTask(i, ui);
                            storage.saveTasksToFile(tasks);
                            return ui.printUnmarkTaskSuccessful(selectedTaskToBeUnmarked);
                        } catch (NumberFormatException e) {
                            return ui.printNumberFormatException();
                        } catch (IndexOutOfBoundsException e) {
                            return ui.printIndexOutOfBoundsException();
                        }
                    }
                } catch (exception.MissingTaskNumberException e) {
                    return e.getMessage();
                }
                // Fallthrough
            case "todo":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingDescriptionException();
                    } else {
                        String description = token[1];
                        Task todo = tasks.addToDo(description, ui);
                        storage.saveTasksToFile(tasks);
                        return ui.printAddTodoSuccessful(todo, tasks.size());
                    }
                } catch (exception.MissingDescriptionException e) {
                    return e.getMessage();
                }
                // Fallthrough
            case "deadline":
                try {
                    if (!token[1].contains("/by")) {
                        throw ChronosException.createInvalidDeadlineException();
                    }
                    String[] descriptionAndBy = token[1].split("/by");
                    String description = descriptionAndBy[0].trim();
                    String dueDate = Parser.formatDateTime(descriptionAndBy[1].trim());
                    Task deadline = tasks.addDeadline(description, dueDate, ui);
                    storage.saveTasksToFile(tasks);
                    return ui.printAddDeadlineSuccessful(deadline, tasks.size());
                } catch (exception.InvalidDeadlineException e) {
                    return e.getMessage();
                } catch (Exception e) {
                    ArrayList<String> message = new ArrayList<>();
                    message.add("Invalid command.");
                    message.add("Please include a task description and due date following the example below:");
                    message.add("e.g. deadline return library book /by 2024-09-22 15:00");
                    return String.join(" ", message);
                }
                // Fallthrough
            case "event":
                try {
                    if (!token[1].contains("/from") && !token[1].contains("/to")) {
                        throw ChronosException.createInvalidEventException();
                    }
                    String[] descriptionAndDate = token[1].split("/from");
                    String description = descriptionAndDate[0].trim();
                    String[] fromAndTo = descriptionAndDate[1].split("/to");
                    String fromDateAndTime = Parser.formatDateTime(fromAndTo[0].trim());
                    String toDateAndTime = Parser.formatDateTime(fromAndTo[1].trim());
                    Task event = tasks.addEvent(description, fromDateAndTime, toDateAndTime, ui);
                    storage.saveTasksToFile(tasks);
                    return ui.printAddEventSuccessful(event, tasks.size());
                } catch (exception.InvalidEventException e) {
                    return e.getMessage();
                } catch (Exception e) {
                    ArrayList<String> message = new ArrayList<>();
                    message.add("Invalid command.");
                    message.add("Please include a task description and due date following the example below:");
                    message.add("e.g. event concert /from 2024-02-16 18:00 /to 2024-02-16 20:00");
                    return String.join(" ", message);
                }
                // Fallthrough
            case "delete":
                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingTaskNumberException();
                    } else {
                        try {
                            int i = Integer.parseInt(token[1]);
                            Task deletedTask = tasks.deleteTask(i, ui);
                            storage.saveTasksToFile(tasks);
                            return ui.printDeleteTaskSuccessful(deletedTask, tasks.size());
                        } catch (NumberFormatException e) {
                            return ui.printNumberFormatException();
                        } catch (IndexOutOfBoundsException e) {
                            return ui.printIndexOutOfBoundsException();
                        }
                    }
                } catch (exception.MissingTaskNumberException e) {
                    return e.getMessage();
                }
                // Fallthrough
            case "find":
                TaskList filteredTasks = new TaskList();

                try {
                    if (token.length != 2 || token[1].trim().isEmpty()) {
                        throw ChronosException.createMissingKeywordException();
                    } else {
                        String[] keywords = token[1].split(" ");

                        for (String keyword : keywords) {
                            for (int i = 1; i < tasks.size() + 1; i++) {
                                Task currentTask = tasks.getTask(i - 1);

                                if (currentTask.getDescription().contains(keyword)
                                        && !filteredTasks.contains(currentTask)) {
                                    filteredTasks.addTask(currentTask);
                                }
                            }
                        }

                        if (filteredTasks.isEmpty()) {
                            throw ChronosException.createKeywordNotFoundException();
                        } else {
                            return ui.printFilteredTasks(filteredTasks);
                        }
                    }
                } catch (exception.MissingKeywordException | exception.KeywordNotFoundException e) {
                    return e.getMessage();
                }
                // Fallthrough
            default:
                try {
                    throw ChronosException.createInvalidCommandException();
                } catch (exception.InvalidCommandException e) {
                    return e.getMessage();
                }
                // Fallthrough
            }
        }
    }
}
