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

    /**
     * Displays a user guide explaining how to use the TaskList features.
     *
     * @return A string containing the user guide.
     */
    public static String help() {
        StringBuilder guide = new StringBuilder("# Skyler TaskList User Guide\n\n");
        guide.append("Welcome to Skyler, your personal task management chatbot!\n\n");

        // 1. Adding Tasks
        guide.append("## 1. Adding Tasks\n");
        guide.append("To add a new task, use one of the following commands:\n\n");
        guide.append("```\n");
        guide.append("todo Buy groceries\n");
        guide.append("deadline Submit report /by 2024-02-29\n");
        guide.append("event Team meeting /from 2024-03-01 /to 2024-03-01\n");
        guide.append("```\n");
        guide.append("Skyler will confirm the addition and update you on the total number of tasks in your list.\n\n");

        // 2. Listing Tasks
        guide.append("## 2. Listing Tasks\n");
        guide.append("To view all tasks in your list, use the following command:\n\n");
        guide.append("```\n");
        guide.append("list\n");
        guide.append("```\n");
        guide.append("Skyler will provide a numbered list of all your tasks.\n\n");

        // 3. Deleting Tasks
        guide.append("## 3. Deleting Tasks\n");
        guide.append("You can delete a task by specifying its number in the list. For example:\n\n");
        guide.append("```\n");
        guide.append("delete 2\n");
        guide.append("```\n");
        guide.append("This will remove the task at position 2 in your list. Skyler will confirm the deletion and update you on the total number of tasks remaining.\n\n");

        // 4. Undoing Tasks
        guide.append("## 4. Undoing Tasks\n");
        guide.append("Undo the last add operation with the following command:\n\n");
        guide.append("```\n");
        guide.append("undo\n");
        guide.append("```\n");
        guide.append("Skyler will confirm the undone operation and update you on the current state of your task list.\n\n");

        // 5. Marking Tasks as Done
        guide.append("## 5. Marking Tasks as Done\n");
        guide.append("To mark a task as done, use the following command:\n\n");
        guide.append("```\n");
        guide.append("mark 3\n");
        guide.append("```\n");
        guide.append("This will mark the task at position 3 as done. Skyler will confirm the action.\n\n");

        // 6. Marking Tasks as Not Done
        guide.append("## 6. Marking Tasks as Not Done\n");
        guide.append("To mark a task as not done (undoing a previous mark), use the following command:\n\n");
        guide.append("```\n");
        guide.append("unmark 3\n");
        guide.append("```\n");
        guide.append("This will unmark the task at position 3. Skyler will confirm the action.\n\n");

        // 7. Finding Tasks
        guide.append("## 7. Finding Tasks\n");
        guide.append("You can search for tasks containing a specific keyword:\n\n");
        guide.append("```\n");
        guide.append("find groceries\n");
        guide.append("```\n");
        guide.append("Skyler will provide a list of tasks that match the keyword.\n\n");

        // 8. Viewing Tasks on a Specific Date
        guide.append("## 8. Viewing Tasks on a Specific Date\n");
        guide.append("To view tasks scheduled for a particular date, use the following command:\n\n");
        guide.append("```\n");
        guide.append("view 2024-02-29\n");
        guide.append("```\n");
        guide.append("Skyler will display tasks with deadlines or events on the specified date.\n\n");

        return guide.toString();
    }

}