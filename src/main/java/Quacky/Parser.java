package quacky;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses the user command and dispatches it to the appropriate handler.
     * @param command The full command entered by the user.
     * @param tasks   The current list of tasks.
     * @param ui      The user interface object for interaction.
     * @return A string result of command execution.
     * @throws QuackyException if an error occurs during command parsing or execution.
     */
    public static String parseCommand(String command, TaskList tasks, UI ui) throws QuackyException {
        String[] keywords = command.split(" ", 2);
        String commandWord = keywords[0];
        switch (commandWord.toLowerCase()) {
        case "list":
            return handleList(tasks,ui);
        case "find":
            String keyword = keywords[1];
            return handleFind(keyword,tasks,ui);
        case "mark":
            int taskToMark = Integer.parseInt(keywords[1]) - 1;
            return handleMark(taskToMark,tasks, ui);
        case "unmark":
            int taskToUnmark = Integer.parseInt(keywords[1]) - 1;
            return handleUnmark(taskToUnmark,tasks, ui);
        case "delete":
            int taskToDelete = Integer.parseInt(keywords[1]) - 1;
            return handleDelete(taskToDelete,tasks,ui);
        case "todo":
            try {
                if (command.trim().equals("todo")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                Todo newTodo = new Todo(keywords[1]);
                return handleTasks(newTodo, tasks, ui);
            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        case "deadline":
            try {
                if (command.trim().equals("deadline")) {
                    throw new QuackyException("Quack? (Please provide a description for your task)");
                }
                String[] parts = command.split(" /by ", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new QuackyException("Quack? (Please provide a date for the deadline)");
                }
                try {
                    LocalDate byDate = LocalDate.parse(parts[1]);
                    Deadline newDeadline = new Deadline(parts[0], byDate);
                    return handleTasks(newDeadline, tasks, ui);
                } catch (DateTimeParseException e) {
                    throw new QuackyException("Quack? (Please provide the correct format for the deadline: YYYY-MM-DD)");
                }
            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        case "event": {
            try {
                if (command.trim().equals("event")) {
                    throw new QuackyException("Quack? (Please provide a description for your event)");
                }
                String[] parts = command.split(" /from | /to ", 3);
                if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                    throw new QuackyException("Quack? (Please provide start and end dates for the event)");
                }
                try {
                    LocalDate fromDate = LocalDate.parse(parts[1]);
                    LocalDate toDate = LocalDate.parse(parts[2]);
                    Event newEvent = new Event(parts[0], fromDate, toDate);
                    return handleTasks(newEvent, tasks, ui);
                } catch (DateTimeParseException e) {
                    throw new QuackyException("Quack? (Please use the correct date format for events: YYYY-MM-DD)");
                }
            } catch (QuackyException e) {
                return ui.showErrorMessage(e);
            }
        }
        case "bye":
            return handleBye(ui);
        default:
            throw new QuackyException("Quack? (I dont know what that means. Try another command)");
        }
    }

    /**
     * Handles the 'list' command and displays all tasks.
     * @param tasks The current list of tasks.
     * @param ui    The user interface object for interaction.
     * @return A string displaying all tasks.
     */
    public static String handleList(TaskList tasks, UI ui) {
        return ui.showList(tasks);
    }

    /**
     * Handles the 'find' command and searches for tasks containing the keyword.
     * @param keyword The keyword to search for within task descriptions.
     * @param tasks   The current list of tasks.
     * @param ui      The user interface object for interaction.
     * @return A string result of the search operation.
     */
    public static String handleFind(String keyword, TaskList tasks, UI ui) {
        TaskList tasksFound = tasks.findTasksByKeyword(keyword);
        assert tasksFound != null:  "This should always return a TaskList";
        if (tasksFound.taskNumber() == 0) {
            return ui.say("No tasks found with the keyword: " + keyword);
        } else {
            return ui.showList(tasksFound);
        }
    }

    /**
     * Handles the 'mark' command and marks a task as complete.
     * @param taskNumber The index of the task to mark as complete.
     * @param tasks      The current list of tasks.
     * @param ui         The user interface object for interaction.
     * @return A string result of the mark operation.
     * @throws QuackyException if the task cannot be found or another error occurs.
     */
    public static String handleMark(int taskNumber, TaskList tasks, UI ui) {
        try {
            assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
            tasks.markCompleteTask(taskNumber);
            return ui.showMarkDone(tasks.printTask(taskNumber));
        } catch (QuackyException e) {
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Handles the 'unmark' command and marks a task as incomplete.
     * @param taskNumber The index of the task to mark as incomplete.
     * @param tasks      The current list of tasks.
     * @param ui         The user interface object for interaction.
     * @return A string result of the unmark operation.
     */
    public static String handleUnmark(int taskNumber, TaskList tasks, UI ui) {
        assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
        tasks.unmarkCompleteTask(taskNumber);
        return ui.showUnmarkDone(tasks.printTask(taskNumber));
    }

    /**
     * Handles the 'delete' command and deletes a task from the list.
     * @param taskNumber The index of the task to be deleted.
     * @param tasks      The current list of tasks.
     * @param ui         The user interface object for interaction.
     * @return A string confirming the deletion of the task.
     */
    public static String handleDelete(int taskNumber, TaskList tasks, UI ui) {
        assert taskNumber >= 0 && taskNumber < tasks.taskNumber() : "Task number must be within the valid range.";
        tasks.deleteTask(taskNumber);
        return ui.showDeleteTask(tasks.taskNumber(), tasks.printTask(taskNumber));
    }

    /**
     * Handles adding new tasks to the task list.
     * @param newTask The new task to be added.
     * @param tasks   The current list of tasks.
     * @param ui      The user interface object for interaction.
     * @return A string confirming the addition of the new task.
     * @throws QuackyException if an error occurs during the addition of the new task.
     */
    public static String handleTasks(Task newTask,TaskList tasks, UI ui) {
        try {
            tasks.addTask(newTask);
            return ui.showAddTask(tasks.taskNumber(), newTask.toString());
        } catch (QuackyException e) {
            return ui.showErrorMessage(e);
        }
    }
    /**
     * Handles the 'bye' command and initiates the shutdown sequence of the application.
     * @param ui The user interface object for interaction.
     * @return A farewell message indicating the application is closing.
     */
    public static String handleBye(UI ui) {
        return ui.showFarewell();
    }
}
