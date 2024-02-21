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
            assert position != -1 && position + 1 < userInput.length() : "Invalid position";
            String dateStr = userInput.substring(position + 1);
            // Validate the date format
            if (isValidDateFormat(dateStr)) {
                throw new DukeException("Please write the date in the format dd/mm/yyyy");
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate dateToCheck = LocalDate.parse(dateStr, dateFormatter);
            return ui.showDeadlinesEventsOnDate(TaskList.getTasks(), TaskList.getTaskNum(), dateToCheck);
        case "find":
            return handleFind(userInput, ui, position);
        case "help":
            return ui.showHelp();
        default:
            throw new DukeException("     Gurl I'm sorry, idk what that means :-(");
        }
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
        String keyword = userInput.substring(position + 1).trim().toLowerCase();

        // Ensure the keyword has at least two characters
        if (keyword.length() < 2) {
            return "     Please provide a keyword with at least two characters.";
        }

        StringBuilder matchingTasks = new StringBuilder();
        int count = 0;

        // Iterate through tasks to find matching ones
        for (int i = 0; i < TaskList.getTaskNum(); i++) {
            Task task = TaskList.getTask(i);
            String taskDescription = task.toString();

            // Check if task description contains the keyword
            if (taskDescription.toLowerCase().contains(keyword)) {
                count++;
                matchingTasks.append(count).append(". ").append(task).append("\n");
            }
        }

        if (count == 0) {
            return "     No matching tasks found.";
        } else {
            return ui.showMatchingTasks(matchingTasks.toString());
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

        if (TaskList.containsTask(String.valueOf(newTask))) {
            return "     This task already exists in the list.";
        }
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

            // Validate the date format
            if (isValidDateFormat(taskStrBy)) {
                throw new DukeException("Please write the date in the format dd/mm/yyyy");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime deadlineDate = LocalDateTime.parse(taskStrBy, formatter);

            Task newTask = new Deadline(taskStr, deadlineDate);
            if (TaskList.containsTask(String.valueOf(newTask))) {
                return "     This task already exists in the list.";
            }
            tasks.addTask(newTask);

            return ui.showAddedTask(newTask, TaskList.getTaskNum());
        } else {
            throw new DukeException("     Invalid command >:((");
        }
    }

    private static boolean isValidDateFormat(String dateString) {
        // Define regex pattern for the date format dd/mm/yyyy
        String regexPattern = "\\d{2}/\\d{2}/\\d{4}";

        // Check if the date string matches the regex pattern
        return !dateString.matches(regexPattern);
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
            String taskStr = userInput.substring(position + 1, posFrom - 1);
            String taskStrFrom = userInput.substring(posFrom + 6, posTo - 1);
            String taskStrTo = userInput.substring(posTo + 4);

            if (taskStr.isEmpty() || taskStrFrom.isEmpty() || taskStrTo.isEmpty()) {
                throw new DukeException("     Invalid command >:((");
            }

            Task newTask = new Event(taskStr, taskStrFrom, taskStrTo);
            if (TaskList.containsTask(String.valueOf(newTask))) {
                return "     This task already exists in the list.";
            }
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
}
