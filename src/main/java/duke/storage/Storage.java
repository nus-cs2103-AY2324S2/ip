package duke.storage;

import java.util.ArrayList;
import java.util.Arrays;

import duke.exceptions.MissingArgumentException;
import duke.exceptions.TaskNotSupportedException;

/**
 * The UI CLI class handles storing of elements required for
 * the application
 *
 * @author Ryan NgWH
 */
public class Storage {
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
            throws MissingArgumentException, TaskNotSupportedException {
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
    public static void markItem(int markIndex) {
        try {
            storageArray.get(markIndex).mark();

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
    public static void unmarkItem(int unmarkIndex) {
        try {
            storageArray.get(unmarkIndex).unmark();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(String.format("  %s", storageArray.get(unmarkIndex).toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Item cannot be unmarked - Item does not exist");
        }
    }

    public static void deleteItem(int deleteIndex) {
        try {
            Task deletedItem = storageArray.remove(deleteIndex);

            System.out.println("Noted. I've removed this task:");
            System.out.println(String.format("  %s", deletedItem.toString()));
            System.out.println(String.format("Now you have %d tasks in the list.", storageArray.size()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Item cannot be deleted - Item does not exist");
        }
    }
}
