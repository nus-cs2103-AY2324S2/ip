import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duchess {
    private TaskList taskList;

    // Declare the scanner as a static field in the class
    private static Scanner scanner = new Scanner(System.in);

    private static final String FILE_PATH = "./data/duchess.txt";

    public Duchess() throws DuchessException {
        this.taskList = new TaskList();
        //loadDataFromFile();
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

    //Load data from file
    /*
    private void loadDataFromFile() throws DuchessException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskFromFileString(line); // Parse task from file line
                if (task != null) {
                    //task.add(task); // Add task to the list
                    //taskCount++;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    //Saves the data to the file after every change, rewriting each task in the list
    private void saveDataToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n"); // Write each task to file
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
    */

    // Method to parse task from a line read from file
    private Task parseTaskFromFileString(String line) throws DuchessException {
        Task task = null;
        // Parse the line and create task objects accordingly
        // Example line format: "T | 1 | read book"
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = parts[3].trim();
                task = new Deadline(description, isDone, by);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, from, to);
                break;
            default:
                System.out.println("Unknown task type: " + type);
        }
        return task;
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
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: mark <taskIndex>");
                    }
                    break;

                case "unmark":
                    if (tokens.length > 1) {
                        int taskIndexToUnmark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.unmarkTaskAsDone(taskIndexToUnmark);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                case "todo":
                    taskList.addToDo(userInput);
                    break;

                case "deadline":
                    taskList.addDeadline(userInput);
                    break;

                case "event":
                    taskList.addEvent(userInput);
                    break;

                case "delete":
                    if (tokens.length > 1) {
                        int taskIndexToDelete = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.deleteTask(taskIndexToDelete);
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
