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
     * Adds a new task to the task list, saves tasks to file, and prints a
     * confirmation message.
     *
     * @param task The task to be added.
     */
    static void addTask(Task task) {
        tasks.add(task);
        Storage.saveTasksToFile();
        System.out.println("Skyler: Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Skyler: Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Lists all tasks in the task list with their respective indices.
     */
    static void listTasks() {
        System.out.println("Skyler: Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Deletes a task from the task list based on the provided user input, saves
     * tasks to file,
     * and prints a confirmation message.
     *
     * @param userInput The user input specifying the task to be deleted.
     * @throws SkylerException If there is an error deleting the task or if the task
     *                         number is invalid.
     */
    public static void deleteTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task removedTask = tasks.remove(taskId - 1);
                System.out.println("Skyler: Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("Skyler: Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("------------------------------------------------------------");
                Storage.saveTasksToFile();
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'delete <task number>'.");
        }
    }

    /**
     * Marks a task as done in the task list based on the provided user input, saves
     * tasks to file,
     * and prints a confirmation message.
     *
     * @param userInput The user input specifying the task to be marked as done.
     * @throws SkylerException If there is an error marking the task as done or if
     *                         the task number is invalid.
     */
    public static void markTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsDone();
                System.out.println("Skyler: Nice! I've marked this task as done:");
                System.out.println("  " + task);
                System.out.println("------------------------------------------------------------");
                Storage.saveTasksToFile();
            } else {
                throw new SkylerException("Invalid task number. Please provide a valid task number.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new SkylerException("Invalid command. Please use 'mark <task number>'.");
        }
    }

    /**
     * Marks a task as not done in the task list based on the provided user input,
     * saves tasks to file,
     * and prints a confirmation message.
     *
     * @param userInput The user input specifying the task to be marked as not done.
     * @throws SkylerException If there is an error marking the task as not done or
     *                         if the task number is invalid.
     */
    public static void unmarkTask(String userInput) throws SkylerException {
        try {
            int taskId = Integer.parseInt(userInput.split(" ")[1]);
            if (isValidTaskId(taskId)) {
                Task task = tasks.get(taskId - 1);
                task.markAsUndone();
                System.out.println("Skyler: OK, I've marked this task as not done yet:");
                System.out.println("  " + task);
                System.out.println("------------------------------------------------------------");
                Storage.saveTasksToFile();
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

    public static void findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("Skyler: No matching tasks found.");
        } else {
            System.out.println("Skyler: Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }

        System.out.println("------------------------------------------------------------");
    }

    /**
     * Views tasks on the specified date, based on the provided user input, and
     * prints them.
     *
     * @param userInput The user input specifying the date to view tasks.
     * @throws SkylerException If there is an error viewing tasks or if the date
     *                         format is invalid.
     */
    public static void viewTasksOnDate(String userInput) throws SkylerException {
        try {
            String dateString = userInput.split(" ")[1];
            LocalDate dateToView = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.println("Skyler: Here are the tasks for "
                    + dateToView.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");

            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    LocalDate deadlineDate = ((Deadline) task).getBy();
                    if (deadlineDate.equals(dateToView)) {
                        System.out.println("  " + task);
                    }
                } else if (task instanceof Event) {
                    LocalDate fromDate = ((Event) task).getFrom();
                    LocalDate toDate = ((Event) task).getTo();
                    if ((dateToView.isEqual(fromDate) || dateToView.isAfter(fromDate)) &&
                            (dateToView.isEqual(toDate) || dateToView.isBefore(toDate))) {
                        System.out.println("  " + task);
                    }
                }
            }

            System.out.println("------------------------------------------------------------");
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new SkylerException("Invalid 'view' command. Please provide a valid date in yyyy-MM-dd format.");
        }
    }
}
