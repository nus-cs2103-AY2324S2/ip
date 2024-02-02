import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duchess {
    private TaskList taskList;
    private Storage storage;

    // Declare the scanner as a static field in the class
    private static Scanner scanner = new Scanner(System.in);

    private static final String FILE_PATH = "./data/duchess.txt";

    public Duchess() throws DuchessException {
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList();
        ArrayList<Task> tasksStored = storage.loadData();
        if (!tasksStored.isEmpty()) {
            this.taskList = new TaskList(storage.loadData());
        }
    }

    public static void main(String[] args) {
        printHorizontalLine();
        printOpeningGreeting();
        printHorizontalLine();

        try {
            Duchess duchess = new Duchess();
            duchess.printEcho();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        } finally {
            //Close scanner
            scanner.close();
        }

        printHorizontalLine();
    }


    //Adds user input to list, exits if user inputs "bye"
    private void printEcho() throws DuchessException {
        // Loop to read user input
        while (true) {
            String userInput = scanner.nextLine();

            // Split user input into tokens
            String[] tokens = userInput.split(" ");

            // Based on user input, change output
            switch (tokens[0].toLowerCase()) {
                case "bye":
                    printClosingGreeting();
                    return;

                case "list":
                    taskList.printTaskList();
                    break;

                case "mark":
                    if (tokens.length > 1) {
                        int taskIndexToMark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.markTaskAsDone(taskIndexToMark);
                        storage.saveData(taskList);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: mark <taskIndex>");
                    }
                    break;

                case "unmark":
                    if (tokens.length > 1) {
                        int taskIndexToUnmark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.unmarkTaskAsDone(taskIndexToUnmark);
                        storage.saveData(taskList);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                case "todo":
                    taskList.addToDo(userInput);
                    storage.saveData(taskList);
                    break;

                case "deadline":
                    taskList.addDeadline(userInput);
                    storage.saveData(taskList);
                    break;

                case "event":
                    taskList.addEvent(userInput);
                    storage.saveData(taskList);
                    break;

                case "delete":
                    if (tokens.length > 1) {
                        int taskIndexToDelete = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.deleteTask(taskIndexToDelete);
                        storage.saveData(taskList);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                default:
                    throw new DuchessException("Oh dear, I can't make out what that is.");
            }
        }
    }

    //Print opening greeting
    private static void printOpeningGreeting() {
        String logo = " ____            __\n"
                + "|  _ \\ _   ______| |      ___  ___  ___\n"
                + "| | | | | | |  __| |__  /  _ \\/ __|/ __|\n"
                + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\\n"
                + "|____/ \\__,_|____|_| |_|\\ ___||___/|___/\n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duchess.");
        System.out.println("What can I do for you today?");
    }


    //Prints closing greeting
    private static void printClosingGreeting() {
        printHorizontalLine();
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    //Prints a Horizontal Line of 50 dashes
    private static void printHorizontalLine() {
        int lineLength = 50; // Specify the length of the line

        // Print the horizontal line
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }

        System.out.println();
    }
}
