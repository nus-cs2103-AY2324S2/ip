package duke.ui;

import java.util.Arrays;
import java.util.Scanner;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.MissingArgumentException;
import duke.parser.Parser;
import duke.storage.Task;
import duke.storage.TaskList;

/**
 * The UI CLI class handles the displaying of UI elements in
 * the application
 *
 * @author Ryan NgWH
 */
public class Cli extends Ui {
    /**
     * Displays the CLI UI of the application
     */
    @Override
    public void startUI(TaskList taskList) {
        // UI Greeting
        String greeting = "------------------------------------------------------------\n" +
                "Hello! I'm Ciara\n" +
                "What can I do for you?\n" +
                "------------------------------------------------------------";

        // UI Goodbye message
        String goodbye = "Bye. Hope to see you again soon!";

        // Display greeting
        System.out.println(greeting);

        // Scanner for getting user input
        Scanner sc = new Scanner(System.in);

        String input;
        // Store and echo user input
        do {
            input = sc.nextLine();
            String[] splitInput = input.split(" ");

            System.out.println("------------------------------------------------------------");

            try {
                switch (splitInput[0].toLowerCase()) {
                case "bye": // Exit
                    // Print exit message
                    System.out.println(goodbye);
                    break;

                case "list": // List items
                    String tasks;

                    // Check if date filter exists
                    if (splitInput.length > 1) {
                        switch (splitInput[1]) {
                        case "/date":
                            try {
                                tasks = taskList.getTasks(Parser.userDateToInstant(splitInput[2], "00:00"));
                            } catch (StringIndexOutOfBoundsException e) {
                                throw new InvalidArgumentException(
                                        "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
                            }
                            break;

                        default:
                            throw new InvalidArgumentException(
                                    String.format("Unknown argument '%s' for the 'list' command", splitInput[1]));
                        }
                    } else {
                        tasks = taskList.getTasks();
                    }

                    System.out.print(tasks);
                    break;

                case "mark": // Mark item
                    if (splitInput.length <= 1) {
                        throw new MissingArgumentException("Missing argument - Index of task required");
                    }

                    // Print success message
                    System.out.println("Nice! I've marked this task as done:");
                    Task markedTask = taskList.markTask(Integer.parseInt(splitInput[1]) - 1);
                    System.out.println(String.format("  %s", markedTask.toString()));
                    break;

                case "unmark": // Unmark item
                    if (splitInput.length <= 1) {
                        throw new MissingArgumentException("Missing argument - Index of task required");
                    }

                    // Print success message
                    System.out.println("OK, I've marked this task as not done yet:");
                    Task unmarkedTask = taskList.unmarkTask(Integer.parseInt(splitInput[1]) - 1);
                    System.out.println(String.format("  %s", unmarkedTask.toString()));
                    break;

                case "delete": // Delete item
                    if (splitInput.length <= 1) {
                        throw new MissingArgumentException("Missing argument - Index of task required");
                    }

                    // Print success message
                    System.out.println("Noted. I've removed this task:");
                    Task deletedTask = taskList.deleteTask(Integer.parseInt(splitInput[1]) - 1);
                    System.out.println(String.format("  %s", deletedTask.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));

                    break;

                default: // Store and echo items
                    // Store item
                    taskList.addTask(splitInput[0], Arrays.copyOfRange(splitInput, 1, splitInput.length));
                    break;
                }
            } catch (Exception exception) {
                System.out.println(String.format("ERROR: %s", exception.getMessage()));
            }

            System.out.println("------------------------------------------------------------");
        } while (!input.equals("bye"));

        sc.close();
    }

    /**
     * Displays warning when application fails to load tasks from file
     */
    public static void displayLoadFromFileWarning() {
        System.out.println("WARNING: Failed to load from file, starting with empty list");
    }
}
