package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser for Duke.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate response.
     * @param userInput The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @return The response to the user input.
     * @throws DukeException If an error occurs during parsing.
     */
    public static String parse(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();
        int position = userInput.indexOf(" ");

        switch (command) {
        case "bye":
            return ui.goodbyeMessage();
        case "list":
            return ui.showTasks(TaskList.getTasks(), TaskList.getTaskNum());
        case "mark":
            return handleTaskStatusChange(userInput, tasks, ui, position, true);
        case "unmark":
            return handleTaskStatusChange(userInput, tasks, ui, position, false);
        case "todo":
            return handleTodo(userInput, tasks, ui, position);
        case "deadline":
            return handleDeadline(userInput, tasks, ui, position);
        case "event":
            return handleEvent(userInput, tasks, ui, position);
        case "delete":
            return handleDelete(userInput, tasks, ui, position);
        case "on":
            return handleOn(userInput, ui, position);
        case "find":
            return handleFind(userInput, ui, position);
        default:
            throw new DukeException("     Gurl I'm sorry, idk what that means :-(");
        }
    }

    /**
     * Parses the user input and returns the appropriate response.
     * @param userInput The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @return The response to the user input.
     */
    private static String handleTaskStatusChange(String userInput, TaskList tasks, Ui ui, int pos, boolean taskStatus) {
        assert pos != -1 && pos + 1 < userInput.length() : "Invalid position";
        String taskStr = userInput.substring(pos + 1);
        int taskNumber = Integer.parseInt(taskStr) - 1;
        assert taskNumber >= 0 && taskNumber < TaskList.getTaskNum() : "Invalid task number";

        if (taskStatus) {
            tasks.markTaskAsDone(taskNumber);
            return ui.showMarkedAsDone(TaskList.getTask(taskNumber));
        } else {
            tasks.markTaskAsNotDone(taskNumber);
            return ui.showMarkedAsNotDone(TaskList.getTask(taskNumber));
        }
    }

    /**
     * Handles the todo command.
     * @param userInput The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param position The position of the command in the user input.
     * @return The response to the user input.
     * @throws DukeException If an error occurs during parsing.
     */
    private static String handleTodo(String userInput, TaskList tasks, Ui ui, int position) throws DukeException {
        assert position != -1 && position + 1 < userInput.length() : "Invalid position";
        String taskStr = userInput.substring(position + 1);

        if (taskStr.isEmpty()) {
            throw new DukeException("     Are you gonna be doing nothing?");
        }

        Task newTask = new Todo(taskStr);
        tasks.addTask(newTask);

        return ui.showAddedTask(newTask, TaskList.getTaskNum());
    }

    /**
     * Handles the deadline command.
     * @param userInput The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param position The position of the command in the user input.
     * @return The response to the user input.
     * @throws DukeException If an error occurs during parsing.
     */
    private static String handleDeadline(String userInput, TaskList tasks, Ui ui, int position) throws DukeException {
        assert position != -1 && position + 1 < userInput.length() : "Invalid position";
        int posBy = userInput.indexOf("/by");
        if (posBy != -1 && posBy + 1 < userInput.length()) {
            String taskStr = userInput.substring(position + 1, posBy - 1);
            String taskStrBy = userInput.substring(posBy + 4);

            if (taskStr.isEmpty() || taskStrBy.isEmpty()) {
                throw new DukeException("     Invalid command >:((");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime deadlineDate = LocalDateTime.parse(taskStrBy, formatter);

            Task newTask = new Deadline(taskStr, deadlineDate);
            tasks.addTask(newTask);

            return ui.showAddedTask(newTask, TaskList.getTaskNum());
        } else {
            throw new DukeException("     Invalid command >:((");
        }
    }

    /**
     * Handles the event command.
     * @param userInput The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param position The position of the command in the user input.
     * @return The response to the user input.
     * @throws DukeException If an error occurs during parsing.
     */
    private static String handleEvent(String userInput, TaskList tasks, Ui ui, int position) throws DukeException {
        int posFrom = userInput.indexOf("/from");
        int posTo = userInput.indexOf("/to");

        assert position != -1 && position + 1 < userInput.length() : "Invalid position";
        if (posFrom != -1 && posFrom + 1
                < userInput.length() && posTo != -1 && posTo + 1 < userInput.length()) {
            String taskStr =
                    userInput.substring(position + 1, posFrom - 1);
            String taskStrFrom =
                    userInput.substring(posFrom + 6, posTo - 1);
            String taskStrTo = userInput.substring(posTo + 4);

            if (taskStr.isEmpty() || taskStrFrom.isEmpty() || taskStrTo.isEmpty()) {
                throw new DukeException("     Invalid command >:((");
            }

            Task newTask = new Event(taskStr, taskStrFrom, taskStrTo);
            tasks.addTask(newTask);

            return ui.showAddedTask(newTask, TaskList.getTaskNum());
        } else {
            throw new DukeException("     Invalid command >:(");
        }
    }

    /**
     * Handles the delete command.
     * @param userInput The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param position The position of the command in the user input.
     * @return The response to the user input.
     * @throws DukeException If an error occurs during parsing.
     */
    private static String handleDelete(String userInput, TaskList tasks, Ui ui, int position) throws DukeException {
        assert position != -1 && position + 1 < userInput.length() : "Invalid position";
        String taskStr = userInput.substring(position + 1);
        int taskNumber = Integer.parseInt(taskStr) - 1;

        if (taskStr.isEmpty()) {
            throw new DukeException("     You're deleting air");
        }

        if (taskNumber >= 0 && taskNumber < TaskList.getTaskNum()) {
            String deleteMessage = ui.showDeleteTask(TaskList.getTask(taskNumber));
            tasks.removeTask(taskNumber);
            return deleteMessage;
        }
        return "     Invalid command >:(";
    }

    /**
     * Handles the on command.
     * @param userInput The user input.
     * @param ui The user interface.
     * @param position The position of the command in the user input.
     * @return The response to the user input.
     */
    private static String handleOn(String userInput, Ui ui, int position) {
        assert position != -1 && position + 1 < userInput.length() : "Invalid position";
        String dateStr = userInput.substring(position + 1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateToCheck = LocalDate.parse(dateStr, dateFormatter);

        return ui.showDeadlinesEventsOnDate(TaskList.getTasks(), TaskList.getTaskNum(), dateToCheck);
    }

    /**
     * Handles the find command.
     * @param userInput The user input.
     * @param ui The user interface.
     * @param position The position of the command in the user input.
     * @return The response to the user input.
     */
    private static String handleFind(String userInput, Ui ui, int position) {
        assert position != -1 && position + 1 < userInput.length() : "Invalid position";
        String keyword = userInput.substring(position + 1);
        return ui.showMatchingTasks(keyword);
    }
}
