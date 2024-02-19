package skyler.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import skyler.task.Deadline;
import skyler.task.Event;
import skyler.task.Task;
import skyler.exception.SkylerException;

public class TaskList {
    private static List<Task> tasks = Storage.getTasks();

    /**
     * Adds a new task to the task list, saves tasks to file, and returns a
     * confirmation message.
     *
     * @param task The task to be added.
     * @return A confirmation message.
     */
    static String addTask(Task task) {
        tasks.add(task);
        Storage.saveTasksToFile();
        return "Skyler: Got it. I've added this task:\n  " + task +
                "\nSkyler: Now you have " + tasks.size()
                + " tasks in the list.\n";
    }

    /**
     * Lists all tasks in the task list with their respective indices.
     *
     * @return A string containing the list of tasks.
     */
    static String listTasks() {
        StringBuilder result = new StringBuilder("Skyler: Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Deletes a task from the task list based on the provided user input, saves
     * tasks to file,
     * and returns a confirmation message.
     *
     * @param userInput The user input specifying the task to be deleted.
     * @return A confirmation message.
     * @throws SkylerException If there is an error deleting the task or if the task
     *                         number is invalid.
     */
    public static String deleteTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task removedTask = tasks.remove(taskId - 1);
                Storage.saveTasksToFile();
                return "Skyler: Noted. I've removed this task:\n  " + removedTask +
                        "\nSkyler: Now you have " + tasks.size()
                        + " tasks in the list.\n";
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'delete <task number>'.");
        }
    }

    /**
     * Undoes the last task operation (add, delete, mark, unmark) and returns a
     * confirmation message.
     *
     * @return A confirmation message for the undone task operation.
     * @throws SkylerException If there is an error undoing the task operation or if
     *                         there are no operations to undo.
     */
    public static String undoTask() throws SkylerException {
        if (tasks.isEmpty()) {
            throw new SkylerException("Nothing to undo. Task list is empty.");
        }

        // Retrieve and remove the last task operation
        Task lastTask = tasks.remove(tasks.size() - 1);
        Storage.saveTasksToFile();

        return "Skyler: Successfully undid the last task operation:\n  " + lastTask +
                "\nSkyler: Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Marks a task as done in the task list based on the provided user input, saves
     * tasks to file, and returns a confirmation message.
     *
     * @param userInput The user input specifying the task to be marked as done.
     * @return A confirmation message.
     * @throws SkylerException If there is an error marking the task as done or if
     *                         the task number is invalid.
     */
    public static String markTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsDone();
                Storage.saveTasksToFile();
                return "Skyler: Nice! I've marked this task as done:\n  " + task +
                        "\n";
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'mark <task number>'.");
        }
    }

    /**
     * Marks a task as not done in the task list based on the provided user input,
     * saves tasks to file, and returns a confirmation message.
     *
     * @param userInput The user input specifying the task to be marked as not done.
     * @return A confirmation message.
     * @throws SkylerException If there is an error marking the task as not done or
     *                         if the task number is invalid.
     */
    public static String unmarkTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsUndone();
                Storage.saveTasksToFile();
                return "Skyler: OK, I've marked this task as not done yet:\n  " + task +
                        "\n";
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'unmark <task number>'.");
        }
    }

    /**
     * Checks if the provided task ID is valid (within the range of existing tasks).
     *
     * @param taskId The task ID to be checked.
     * @return True if the task ID is valid, false otherwise.
     */
    public static boolean isValidTaskId(int taskId) {
        return taskId > 0 && taskId <= tasks.size();
    }

    /**
     * Finds tasks containing the specified keyword and returns the result.
     *
     * @param keyword The keyword to search for.
     * @return A string containing the matching tasks or a message if no matches are
     *         found.
     */
    public static String findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "Skyler: No matching tasks found.\n";
        } else {
            StringBuilder result = new StringBuilder("Skyler: Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Views tasks on the specified date, based on the provided user input, and
     * returns a string.
     *
     * @param userInput The user input specifying the date to view tasks.
     * @return A string containing the tasks for the specified date.
     * @throws SkylerException If there is an error viewing tasks or if the date
     *                         format is invalid.
     */
    public static String viewTasksOnDate(String userInput) throws SkylerException {
        try {
            String dateString = userInput.split(" ")[1];
            LocalDate dateToView = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            StringBuilder result = new StringBuilder("Skyler: Here are the tasks for "
                    + dateToView.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");

            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    LocalDate deadlineDate = ((Deadline) task).getBy();
                    if (deadlineDate.equals(dateToView)) {
                        result.append("  ").append(task).append("\n");
                    }
                } else if (task instanceof Event) {
                    LocalDate fromDate = ((Event) task).getFrom();
                    LocalDate toDate = ((Event) task).getTo();
                    if ((dateToView.isEqual(fromDate) || dateToView.isAfter(fromDate)) &&
                            (dateToView.isEqual(toDate) || dateToView.isBefore(toDate))) {
                        result.append("  ").append(task).append("\n");
                    }
                }
            }

            return result.toString();
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new SkylerException("Invalid 'view' command. Please provide a valid date in yyyy-MM-dd format.");
        }
    }
}
