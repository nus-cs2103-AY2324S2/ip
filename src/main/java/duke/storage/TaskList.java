package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;

import duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.MissingArgumentException;
import duke.exceptions.MissingTaskException;
import duke.exceptions.StorageException;
import duke.exceptions.TaskCorruptedException;
import duke.exceptions.TaskNotSupportedException;
import duke.parser.Parser;
import duke.ui.Cli;

/**
 * The TaskList class handles storing of tasks required for
 * the application
 *
 * @author Ryan NgWH
 */
public class TaskList {
    /**
     * Array used to store tasks for the application
     */
    private ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Create a new task list with values loaded from a file
     *
     * @param file File to load tasks from
     */
    public TaskList(File file) {
        try {
            Storage.loadFromFile(this, file);
        } catch (TaskNotSupportedException | TaskCorruptedException | FileNotFoundException e) {
            Cli.printLoadFromFileWarning();
        }
    }

    /**
     * Get the number of stored tasks
     *
     * @return Number of stored tasks
     */
    public int size() {
        return taskArray.size();
    }

    /**
     * Adds a task
     *
     * @param item      Type of item to be stored
     * @param arguments Arguments of the item type
     */
    public void addTask(String item, String[] arguments)
            throws MissingArgumentException,
            TaskNotSupportedException,
            IOException,
            InvalidArgumentException {
        // Create task to be inserted
        Task task;
        String description;

        switch (item.toLowerCase()) {
        case "todo":
            if (arguments.length <= 0) {
                throw new MissingArgumentException("Argument missing - Description of a todo cannot be empty");
            }

            description = String.join(" ", arguments);
            task = new Todo(description);
            break;

        case "deadline":
            // Get index of '/by' argument
            int byIndex = -1;
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i].equals("/by")) {
                    byIndex = i;
                    break;
                }
            }

            if (byIndex == -1) {
                throw new MissingArgumentException("Argument '/by' missing");
            }

            // Extract task description & due date
            description = String.join(" ", Arrays.copyOfRange(arguments, 0, byIndex));

            try {
                String date = arguments[byIndex + 1];
                String time = arguments[byIndex + 2];

                // Create new task
                task = new Deadline(description, Parser.userDateToInstant(date, time));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException(
                        "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'");
            }
            break;

        case "event":
            // Get index of '/from' and '/to' argument
            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < arguments.length; i++) {
                if (fromIndex != -1 && toIndex != -1) {
                    break;
                }

                if (fromIndex == -1 && arguments[i].equals("/from")) {
                    fromIndex = i;
                }

                if (toIndex == -1 && arguments[i].equals("/to")) {
                    toIndex = i;
                }
            }

            if (fromIndex == -1) {
                throw new MissingArgumentException("Argument '/from' missing");
            } else if (toIndex == -1) {
                throw new MissingArgumentException("Argument '/to' missing");
            }

            // Extract task description
            description = String.join(" ", Arrays.copyOfRange(arguments, 0, fromIndex));

            try {
                // Extract start date
                String fromDate = arguments[fromIndex + 1];
                String fromTime = arguments[fromIndex + 2];

                // Extract end date
                String toDate = arguments[toIndex + 1];
                String toTime = arguments[toIndex + 2];

                // Create new task
                task = new Event(description, Parser.userDateToInstant(fromDate, fromTime),
                        Parser.userDateToInstant(toDate, toTime));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException(
                        "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'");
            }
            break;

        default:
            throw new TaskNotSupportedException(
                    String.format("Task '%s' is not yet supported. Please try again with another task.", item));
        }

        // Add item to storage
        taskArray.add(task);

        // Save to file
        JSONArray jsonArray = new JSONArray(taskArray);
        Storage.saveToFile(jsonArray, Duke.SAVE_FILE);

        // Print confirmation message
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", taskArray.size()));
    }

    /**
     * Adds a task
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }

    /**
     * Remove an item from task list
     *
     * @param deleteIndex Index of the item to delete
     */
    public Task deleteTask(int deleteIndex) throws IOException, MissingTaskException {
        try {
            // Delete item
            Task deletedTask = taskArray.remove(deleteIndex);

            // Save to file
            JSONArray jsonArray = new JSONArray(taskArray);
            Storage.saveToFile(jsonArray, Duke.SAVE_FILE);

            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException("Task does not exist");
        }
    }

    /**
     * Mark a task as completed
     *
     * @param markIndex Index of the item to mark
     *
     * @return Task succesfully marked as completed
     */
    public Task markTask(int markIndex) throws StorageException, MissingTaskException {
        try {
            // Mark task
            taskArray.get(markIndex).mark();

            // Save to file
            JSONArray jsonArray = new JSONArray(taskArray);
            Storage.saveToFile(jsonArray, Duke.SAVE_FILE);

            return taskArray.get(markIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException("Task does not exist");
        } catch (IOException e) {
            throw new StorageException(String.format("Failed to save to file - %s", e.getMessage()));
        }
    }

    /**
     * Unmark a task as not completed
     *
     * @param unmarkIndex Index of the item to unmark
     */
    public Task unmarkTask(int unmarkIndex) throws IOException, MissingTaskException {
        try {
            // Unmark task
            taskArray.get(unmarkIndex).unmark();

            // Save to file
            JSONArray jsonArray = new JSONArray(taskArray);
            Storage.saveToFile(jsonArray, Duke.SAVE_FILE);

            return taskArray.get(unmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException("Task does not exist");
        }
    }

    /**
     * Get all items in storage
     */
    public String getTasks() {
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < taskArray.size(); i++) {
            tasks.append(String.format("%d.%s\n", i + 1, taskArray.get(i).toString()));
        }

        return tasks.substring(0, tasks.length() - 1).toString();
    }

    /**
     * Get all items in storage filtered by date
     *
     * @param date Date to filter
     */
    public String getTasks(Instant date) throws InvalidArgumentException {
        StringBuilder tasks = new StringBuilder();
        try {
            int printIndex = 1;

            for (int i = 0; i < taskArray.size(); i++) {
                Task task = taskArray.get(i);

                // Check if date lies on deadline due date or within event start and end date
                if ((task instanceof Deadline && ((Deadline) task).isOn(date))
                        || (task instanceof Event && ((Event) task).encompasses(date))) {
                    tasks.append(String.format("%d.%s\n", printIndex, taskArray.get(i).toString()));
                    printIndex++;
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
        }

        // Remove trailing '\n' characters
        if (tasks.lastIndexOf("\n") != -1) {
            return tasks.substring(0, tasks.length() - 1);
        }
        return tasks.toString();
    }
}
