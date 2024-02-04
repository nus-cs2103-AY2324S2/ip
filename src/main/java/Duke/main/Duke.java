package Duke.main;

import Duke.activityAndUtility.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@code Duke} class serves as the entry point for the Duke application, a text-based task management system.
 * This class handles user input, allowing users to add, delete, mark, unmark tasks, and list all tasks. It interacts
 * with the {@link ActivityList} class to perform these operations and maintains a simple command-line interface for
 * user interaction.
 */
public class Duke {
    /**
     * The main method serves as the application's entry point. It initializes the application, displays a welcome
     * message, and enters a loop to accept and process user commands until the "bye" command is entered.
     *
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ActivityList list = new ActivityList();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Dad\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            String firstWord = input.split(" ")[0];
            if (Objects.equals(firstWord, "list")) {
                list.printActivity();
            } else if (Objects.equals(firstWord, "delete")) {
                list.deleteActivity(input.substring(input.indexOf(" ") + 1));
            } else if (Objects.equals(firstWord, "bye")) {
                break;
            } else if (Objects.equals(firstWord, "mark") || Objects.equals(firstWord, "unmark")) {
                list.markActivity(firstWord, input.substring(input.indexOf(" ") + 1));
            } else {
                list.addActivity(firstWord, input);
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tLater");
        System.out.println("\t____________________________________________________________");
    }

    /**
     * The {@code ActivityList} class manages a list of {@link Activity} objects. It supports adding, printing,
     * marking, and deleting activities. This class interacts with a local file system through {@link LocalList}
     * to persist activities across sessions.
     */
    static class ActivityList {
        private final ArrayList<Activity> activities;
        private final ArrayList<String> searchTable;
        private final String filePath = "./data/duke.txt";
        private final LocalList localList;


        /**
         * Constructs an {@code ActivityList} instance. It initializes the activity list by loading stored activities
         * from a file, and prepares a search table for quick access to activities by name.
         */
        public ActivityList() {
            this.localList = new LocalList(filePath);
            this.activities = localList.load();
            this.searchTable = new ArrayList<>();
            for (Activity activity : activities) {
                searchTable.add(activity.getName());
            }
        }

        /**
         * Adds a new activity of the specified type with the given input to the list.
         * It supports adding todos, deadlines, and events, parsing the input accordingly.
         * After adding an activity, it saves the updated list to the file.
         *
         * @param type  The type of activity to add ("todo", "deadline", or "event").
         * @param input The detailed input string for creating the activity, including its description and, if applicable,
         *              its deadline or event timings.
         * @throws RuntimeException If the input format is invalid or the activity type is unknown.
         */
        public void addActivity(String type, String input) {
            Activity activity;
            int index = input.indexOf(" ") + 1;
            String subStr = input.substring(index);

            switch (type) {
                case "todo":
                    activity = new Todo("X", subStr); // Pass only the relevant substring
                    break;
                case "deadline":
                    String[] deadlineParts = subStr.split(" /", 2);
                    if (deadlineParts.length == 2) {
                        activity = new Deadline("X", deadlineParts[0], deadlineParts[1]); // Adjust Duke.main.Duke.activity.Deadline constructor to accept date and time
                    } else {
                        throw new RuntimeException("Invalid deadline format");
                    }
                    break;
                case "event":
                    String[] eventParts = subStr.split(" /", 3);
                    if (eventParts.length == 3) {
                        activity = new Event("X", eventParts[0], eventParts[1], eventParts[2]); // Adjust Duke.main.Duke.activity.Event constructor
                    } else {
                        throw new RuntimeException("Invalid event format");
                    }
                    break;
                default:
                    throw new RuntimeException("Unknown activity type: " + type);
            }

            this.searchTable.add(activity.getName());
            this.activities.add(activity);
            System.out.println("\t____________________________________________________________");
            System.out.print("\tadded: ");
            activity.printActivity();
            System.out.println("\t____________________________________________________________");
            localList.save(activities);
        }

        /**
         * Prints all activities in the list. Each activity is printed with its index in the list, followed by its details.
         */
        public void printActivity() {
            System.out.println("\t____________________________________________________________");
            System.out.println("\tList: ");
            for (int i = 0; i < activities.size(); i++) {
                System.out.format("%s. ", i + 1);
                activities.get(i).printActivity();
            }
            System.out.println("\t____________________________________________________________");
        }

        /**
         * Marks an activity identified by a key as complete or incomplete based on the input command.
         * It searches for the activity in the list and applies the mark operation.
         *
         * @param input The command indicating whether to mark the activity as complete ("mark") or incomplete ("unmark").
         * @param key   The name or identifier of the activity to be marked.
         * @throws RuntimeException If the activity cannot be found in the list.
         */
        public void markActivity(String input, String key) {
            if (searchTable.contains(key)) {
                int index = this.searchTable.indexOf(key);
                this.activities.get(index).mark(input);
            } else {
                throw new RuntimeException("can't find activity");
            }
            localList.save(activities);
        }

        /**
         * Deletes an activity from the list based on its index.
         * The method also handles invalid indices or input formats gracefully.
         *
         * @param input The string representation of the index (1-based) of the activity to delete.
         * @throws RuntimeException If the input is not a valid integer or the index is out of bounds.
         */
        public void deleteActivity(String input) {
            try {
                int index = Integer.parseInt(input);
                Activity removed = activities.remove(index - 1);
                searchTable.remove(index - 1);
                System.out.println("\t____________________________________________________________");
                System.out.format("\tI have removed: ");
                removed.printActivity();
                System.out.println("\t____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("The string does not contain a valid integer.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("to long or too short won't do the job");
            }
            localList.save(activities);
        }
    }
}


