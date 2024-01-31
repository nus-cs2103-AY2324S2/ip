import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duchess {
    private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private int taskCount;
    // Declare the scanner as a static field in the class
    private static Scanner scanner = new Scanner(System.in);

    private static final String FILE_PATH = "./data/duchess.txt";

    // Enum to represent task types
    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public Duchess() throws DuchessException {
        tasks = new ArrayList<>();
        taskCount = 0;
        loadDataFromFile();
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
                    tasks.add(task); // Add task to the list
                    taskCount++;
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

    //Add a ToDo to the task list
    private void addToDo(String userInput) throws DuchessException {
        String[] toDoTokens = userInput.split("todo"); //Split to find description
        if (toDoTokens.length > 1) {
            String description = toDoTokens[1].trim(); //Trim to only keep description
            ToDo newToDo = new ToDo(description);
            addTask(newToDo, TaskType.TODO);
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: todo <description>");
        }
    }

    //Add a Deadline to the taskList
    private void addDeadline(String userInput) throws DuchessException {
        String[] deadlineTokens = userInput.split("deadline");

        if (deadlineTokens.length > 1) {
            // Split further to extract description and deadline details
            String[] details = deadlineTokens[1].trim().split("/by");

            if (details.length > 1) {
                String description = details[0].trim();
                String by = details[1].trim(); // by is everything after
                Deadline newDeadline = new Deadline(description, by);
                addTask(newDeadline, TaskType.DEADLINE);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: deadline <description> /by <deadline>");
        }
    }

    //Add an Event to the task list
    private void addEvent(String userInput) throws DuchessException {
        String[] eventTokens = userInput.split("event");

        if (eventTokens.length > 1) {
            // Split further to extract description and event details
            String[] details = eventTokens[1].trim().split("/from|/to"); // Means can use either /from or /to as delimiter

            if (details.length > 2) {
                String description = details[0].trim();
                String from = details[1].trim(); // from is everything after
                String to = details[2].trim();   // to is everything after

                Event newEvent = new Event(description, from, to);
                addTask(newEvent, TaskType.EVENT);
            } else {
                throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DuchessException("Oh dear! That is an invalid command. Try: event <description> /from <start> /to <end>");
        }
    }

    private void printTaskList() {
        printHorizontalLine();
        if (taskCount == 0) {
            System.out.println(" No tasks have been added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        printHorizontalLine();
    }

    //Add a task to task list
    private void addTask(Task task, TaskType taskType) throws DuchessException {
        if (taskCount < MAX_TASKS) {
            tasks.add(task);
            taskCount++;
            saveDataToFile();

            printHorizontalLine();
            System.out.println(" Understood. I've added this " + taskType + " task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            printHorizontalLine();
        } else {
            throw new DuchessException("The task list is full. I cannot add more tasks.");
        }
    }

    private void deleteTask(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            Task deletedTask = tasks.remove(taskIndex);
            taskCount--;
            saveDataToFile();

            printHorizontalLine();
            System.out.println(" Understood. I've deleted this task:");
            System.out.println(deletedTask.toString());
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Mark a task as done
    private void markTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
            saveDataToFile();
            printHorizontalLine();
            System.out.println(" Perfect! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex).toString());
            printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Unmark a task as done
    private void unmarkTaskAsDone(int taskIndex) throws DuchessException {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).unmarkAsDone();
            saveDataToFile();
            printHorizontalLine();
            System.out.println(" Understood, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex).toString());
            printHorizontalLine();
        } else {
            throw new DuchessException("Invalid task index.");
        }
    }

    // Check if the task index is valid
    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < taskCount;
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
                    printTaskList();
                    break;

                case "mark":
                    if (tokens.length > 1) {
                        int taskIndexToMark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        markTaskAsDone(taskIndexToMark);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: mark <taskIndex>");
                    }
                    break;

                case "unmark":
                    if (tokens.length > 1) {
                        int taskIndexToUnmark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        unmarkTaskAsDone(taskIndexToUnmark);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                case "todo":
                    addToDo(userInput);
                    break;

                case "deadline":
                    addDeadline(userInput);
                    break;

                case "event":
                    addEvent(userInput);
                    break;

                case "delete":
                    if (tokens.length > 1) {
                        int taskIndexToDelete = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        deleteTask(taskIndexToDelete);
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
