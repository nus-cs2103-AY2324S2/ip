package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import duke.exceptions.MissingArgumentException;
import duke.exceptions.TaskNotSupportedException;
import duke.storage.Task.TaskType;

/**
 * The UI CLI class handles storing of elements required for
 * the application
 *
 * @author Ryan NgWH
 */
public class Storage {
    /**
     * File to save storage to
     */
    private static final File saveFile = new File("data/tasks.json");

    /**
     * Array used to store objects for the application
     */
    private static ArrayList<Task> storageArray = new ArrayList<>();

    /**
     * Method to store items for the application
     *
     * @param item      Type of item to be stored
     * @param arguments Arguments of the item type
     */
    public static void storeItem(String item, String[] arguments)
            throws MissingArgumentException, TaskNotSupportedException,
            IOException {
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
            String dueDate = String.join(" ", Arrays.copyOfRange(arguments, byIndex + 1, arguments.length));

            task = new Deadline(description, dueDate);
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

            // Extract task description, start and end date
            description = String.join(" ", Arrays.copyOfRange(arguments, 0, fromIndex));
            String startDate = String.join(" ", Arrays.copyOfRange(arguments, fromIndex + 1, toIndex));
            String endDate = String.join(" ", Arrays.copyOfRange(arguments, toIndex + 1, arguments.length));

            task = new Event(description, startDate, endDate);
            break;

        default:
            throw new TaskNotSupportedException(
                    String.format("Task '%s' is not yet supported. Please try again with another task.", item));
        }

        // Add item to storage
        storageArray.add(task);

        // Save to file
        saveStorageToFile(saveFile);

        // Print confirmation message
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", storageArray.size()));
    }

    /**
     * Method to print all items in storage to standard output
     */
    public static void listItems() {
        for (int i = 0; i < storageArray.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, storageArray.get(i).toString()));
        }
    }

    /**
     * Method to mark an item in storage
     *
     * @param markIndex Index of the item to mark
     */
    public static void markItem(int markIndex) throws IOException {
        try {
            // Mark Item
            storageArray.get(markIndex).mark();

            // Save to file
            saveStorageToFile(saveFile);

            // Print message
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(String.format("  %s", storageArray.get(markIndex).toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Item cannot be marked - Item does not exist");
        }
    }

    /**
     * Method to unmark an item in storage
     *
     * @param unmarkIndex Index of the item to mark
     */
    public static void unmarkItem(int unmarkIndex) throws IOException {
        try {
            // Unmark item
            storageArray.get(unmarkIndex).unmark();

            // Save to file
            saveStorageToFile(saveFile);

            // Print message
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(String.format("  %s", storageArray.get(unmarkIndex).toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Item cannot be unmarked - Item does not exist");
        }
    }

    /**
     * Deletes an item from storage
     *
     * @param unmarkIndex Index of the item to mark
     */
    public static void deleteItem(int deleteIndex) throws IOException {
        try {
            // Delete item
            Task deletedItem = storageArray.remove(deleteIndex);

            // Save to file
            saveStorageToFile(saveFile);

            // Print message
            System.out.println("Noted. I've removed this task:");
            System.out.println(String.format("  %s", deletedItem.toString()));
            System.out.println(String.format("Now you have %d tasks in the list.", storageArray.size()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Item cannot be deleted - Item does not exist");
        }
    }

    /**
     * Writes storage array to file in JSON format
     *
     * @param file Path of the file to write to
     */
    private static void saveStorageToFile(File file) throws IOException {
        // Create JSON array to be written to file
        JSONArray jsonStorage = new JSONArray(storageArray);

        // Create file (if required)
        file.createNewFile();

        // Check if file is writable
        if (!file.canWrite()) {
            throw new IOException(String.format("File '%s' cannot be written to", file.getAbsolutePath().toString()));
        }

        // Write to file
        FileWriter writer = new FileWriter(file);
        jsonStorage.write(writer, 2, 0);
        writer.close();
    }

    /**
     * Populate storage array from file in JSON format
     *
     * @param file File to load values from
     */
    private static void loadStorageFromFile(File file) throws TaskNotSupportedException, FileNotFoundException {
        // Read stored tasks from file
        FileInputStream input = new FileInputStream(file);
        JSONArray jsonStorage = new JSONArray(new JSONTokener(input));

        // Populate storage array
        for (int i = 0; i < jsonStorage.length(); i++) {
            // Get json entry
            JSONObject entry = jsonStorage.getJSONObject(i);

            // Parse JSON entry to task
            Task task;
            switch (TaskType.valueOf(entry.getString("type"))) {
            case TODO:
                task = new Todo(entry.getString("description"), entry.getBoolean("isDone"));
                break;

            case DEADLINE:
                task = new Deadline(entry.getString("description"),
                        entry.getString("dueDate"),
                        entry.getBoolean("isDone"));
                break;

            case EVENT:
                task = new Event(entry.getString("description"),
                        entry.getString("startDate"),
                        entry.getString("endDate"),
                        entry.getBoolean("isDone"));
                break;
            default:
                throw new TaskNotSupportedException(
                        String.format("Task '%s' not currently supported", entry.getString("type")));
            }

            // Add task to storage array
            storageArray.add(task);
        }
    }

    /**
     * Initialise storage for application
     */
    public static void initialiseStorage() {
        try {
            loadStorageFromFile(saveFile);
        } catch (TaskNotSupportedException e) {
            System.out.println(String.format("WARNING: %s, some stored tasks will not be loaded", e.getMessage()));
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: File not found, stored tasks will not be loaded");
        }
    }
}
