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
     * Checks if the length of input for a command with a single argument is equals to 1.
     *
     * @param lengthOfInput Length of input provided by the user.
     * @return True if the length of input is equals to 1, else False.
     */
    public static boolean isNotSingleArgument(int lengthOfInput) {
        return lengthOfInput != 1;
    }

    /**
     * Checks if the length of input for a command with double arguments is equals to 2.
     *
     * @param lengthOfInput Length of input provided by the user.
     * @return True if the length of input is equals to 2, else False.
     */
    public static boolean isNotDoubleArgument(int lengthOfInput) {
        return lengthOfInput != 2;
    }

    /**
     * Parses and executes bye command and returns Chronos' response.
     * @param token User input starting with bye.
     * @return Chronos' response to bye command.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteBye(String[] token) throws ChronosException {
        try {
            if (isNotSingleArgument(token.length)) {
                throw ChronosException.createInvalidByeException();
            } else {
                return Ui.bidGoodbye();
            }
        } catch (exception.InvalidByeException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses and executes help command and returns Chronos' response.
     *
     * @param token User input starting with help.
     * @return Chronos' response to help command.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteHelp(String[] token) throws ChronosException {
        try {
            if (isNotSingleArgument(token.length)) {
                throw ChronosException.createInvalidHelpException();
            } else {
                return Ui.printHelp();
            }
        } catch (exception.InvalidHelpException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses and executes list command and returns Chronos' response.
     *
     * @param token User input starting with list.
     * @param ui Constructed Ui object.
     * @param tasks List of tasks.
     * @return Chronos' response to list command.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteList(String[] token, Ui ui, TaskList tasks) throws ChronosException {
        try {
            if (isNotSingleArgument(token.length)) {
                throw ChronosException.createInvalidListException();
            } else if (tasks.isEmpty()) {
                return ui.printNoOutstandingTasks();
            } else {
                return ui.printTasks(tasks);
            }
        } catch (exception.InvalidListException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses and executes mark command and returns Chronos' response.
     *
     * @param token User input starting with mark.
     * @param ui Constructed Ui object.
     * @param storage Text file containing list of tasks.
     * @param tasks List of tasks.
     * @return Chronos' response to mark command.
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteMark(String[] token, Ui ui, Storage storage, TaskList tasks)
            throws IOException, ChronosException {
        try {
            if (isNotDoubleArgument(token.length) || token[1].trim().isEmpty()) {
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
    }

    /**
     * Parses and executes unmark command and returns Chronos' response.
     *
     * @param token User input starting with unmark.
     * @param ui Constructed Ui object.
     * @param storage Text file containing list of tasks.
     * @param tasks List of tasks.
     * @return Chronos' response to unmark command.
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteUnmark(String[] token, Ui ui, Storage storage, TaskList tasks)
            throws IOException, ChronosException {
        try {
            if (isNotDoubleArgument(token.length) || token[1].trim().isEmpty()) {
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
    }

    /**
     * Parses and executes todo command and returns Chronos' response.
     *
     * @param token User input starting with todo.
     * @param ui Constructed Ui object.
     * @param storage Text file containing list of tasks.
     * @param tasks List of tasks.
     * @return Chronos' response to todo command.
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteTodo(String[] token, Ui ui, Storage storage, TaskList tasks)
            throws IOException, ChronosException {
        try {
            if (isNotDoubleArgument(token.length) || token[1].trim().isEmpty()) {
                throw ChronosException.createMissingDescriptionException();
            } else {
                String description = token[1];
                Task todo = tasks.addToDo(description, ui);
                assert !tasks.isEmpty() : "Task list should not be empty.";
                storage.saveTasksToFile(tasks);
                return ui.printAddTodoSuccessful(todo, tasks.size());
            }
        } catch (exception.MissingDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses and executes deadline command and returns Chronos' response.
     *
     * @param token User input starting with deadline.
     * @param ui Constructed Ui object.
     * @param storage Text file containing list of tasks.
     * @param tasks List of tasks.
     * @return Chronos' response to deadline command.
     */
    public static String parseAndExecuteDeadline(String[] token, Ui ui, Storage storage, TaskList tasks) {
        try {
            if (!token[1].contains("/by")) {
                throw ChronosException.createInvalidDeadlineException();
            }
            String[] descriptionAndBy = token[1].split("/by");
            String description = descriptionAndBy[0].trim();
            String dueDate = Parser.formatDateTime(descriptionAndBy[1].trim());
            Task deadline = tasks.addDeadline(description, dueDate, ui);
            assert !tasks.isEmpty() : "Task list should not be empty.";
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
    }

    /**
     * Parses and executes event command and returns Chronos' response.
     *
     * @param token User input starting with event.
     * @param ui Constructed Ui object.
     * @param storage Text file containing list of tasks.
     * @param tasks List of tasks.
     * @return Chronos' response to event command.
     */
    public static String parseAndExecuteEvent(String[] token, Ui ui, Storage storage, TaskList tasks) {
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
            assert !tasks.isEmpty() : "Task list should not be empty.";
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
    }

    /**
     * Parses and executes delete command and returns Chronos' response.
     *
     * @param token User input starting with delete.
     * @param ui Constructed Ui object.
     * @param storage Text file containing list of tasks.
     * @param tasks List of tasks.
     * @return Chronos' response to delete command.
     */
    public static String parseAndExecuteDelete(String[] token, Ui ui, Storage storage, TaskList tasks)
            throws IOException, ChronosException {
        try {
            if (isNotDoubleArgument(token.length) || token[1].trim().isEmpty()) {
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
    }

    /**
     * Parses and executes find command and returns Chronos' response.
     *
     * @param token User input starting with find.
     * @param ui Constructed Ui object.
     * @param tasks List of tasks.
     * @return Chronos' response to find command.
     */
    public static String parseAndExecuteFind(String[] token, Ui ui, TaskList tasks) throws ChronosException {
        TaskList filteredTasks = new TaskList();

        try {
            if (isNotDoubleArgument(token.length) || token[1].trim().isEmpty()) {
                throw ChronosException.createMissingKeywordException();
            } else {
                String[] keywords = token[1].split(" ");

                for (String keyword : keywords) {
                    for (int i = 1; i < tasks.size() + 1; i++) {
                        Task currentTask = tasks.getTask(i - 1);
                        assert currentTask != null : "Current task should not be null.";

                        if (currentTask.getDescription().contains(keyword)
                                && !filteredTasks.contains(currentTask)) {
                            filteredTasks.addTask(currentTask);
                        }
                    }
                }

                if (filteredTasks.isEmpty()) {
                    throw ChronosException.createKeywordNotFoundException();
                } else {
                    return ui.printTasks(filteredTasks);
                }
            }
        } catch (exception.MissingKeywordException | exception.KeywordNotFoundException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses and executes invalid command and returns Chronos' response.
     *
     * @return Chronos' response to an invalid command.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static String parseAndExecuteInvalid() throws ChronosException {
        try {
            throw ChronosException.createInvalidCommandException();
        } catch (exception.InvalidCommandException e) {
            return e.getMessage();
        }
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

        switch(token[0]) {
        case "bye":
            return parseAndExecuteBye(token);
        case "sos":
        case "help":
            return parseAndExecuteHelp(token);
            // Fallthrough
        case "ls":
        case "list":
            return parseAndExecuteList(token, ui, tasks);
            // Fallthrough
        case "mk":
        case "mark":
            return parseAndExecuteMark(token, ui, storage, tasks);
            // Fallthrough
        case "umk":
        case "unmark":
            return parseAndExecuteUnmark(token, ui, storage, tasks);
            // Fallthrough
        case "td":
        case "todo":
            return parseAndExecuteTodo(token, ui, storage, tasks);
            // Fallthrough
        case "dl":
        case "deadline":
            return parseAndExecuteDeadline(token, ui, storage, tasks);
            // Fallthrough
        case "ev":
        case "event":
            return parseAndExecuteEvent(token, ui, storage, tasks);
            // Fallthrough
        case "rm":
        case "delete":
            return parseAndExecuteDelete(token, ui, storage, tasks);
            // Fallthrough
        case "f":
        case "find":
            return parseAndExecuteFind(token, ui, tasks);
            // Fallthrough
        default:
            return parseAndExecuteInvalid();
            // Fallthrough
        }
    }
}
