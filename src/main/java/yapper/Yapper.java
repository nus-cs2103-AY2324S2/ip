package yapper;
import yapper.tasks.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.io.InputStream;
import java.util.stream.Collectors;

/**
 * The Yapper class is the main class that handles user input and manages tasks.
 * It includes methods for processing user input, displaying messages, and interacting with storage.
 */
public class Yapper {
    private static final String FILE_PATH = "./src/main/java/data/yapper.Yapper.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static List<Task> tasks;
    private final Scanner userScanner;
    private static Ui ui;
    private final Storage storage;

    /**
     * Constructs a new Yapper instance.
     *
     * @param filePath      The file path for task data.
     * @param inputStream   The input stream for user input.
     */
    public Yapper(String filePath, InputStream inputStream) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YapperException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        userScanner = new Scanner(inputStream);
    }

    /**
     * Runs the Yapper application, displaying welcome messages and handling user input.
     */
    public void run() {
        ui.showWelcomeMessage();
        ui.showInstructions();

        try {
            while (true) {
                ui.showUserPrompt();
                String userInput = userScanner.nextLine();
                processUserInput(userInput);
                this.storage.saveTasks(tasks);
            }
        } catch (NoSuchElementException e) {
            ui.showError("No input provided.");
        } catch (YapperException e) {
            ui.showError(e.getMessage());
        } finally {
            userScanner.close();
            }
        }


    /**
     * Processes the various user inputs.
     *
     * @param userInput User input in string format.
     * @throws YapperException If any of user inputs is invalid.
     */
    private static void processUserInput(String userInput) throws YapperException {

        if (userInput.equalsIgnoreCase("list")) {
            ui.showTaskList(tasks);
        } else if (userInput.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsDone();
                ui.showMarkedDoneMessage(tasks.get(taskNumber));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to mark as done.");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsNotDone();
                ui.showMarkedNotDoneMessage(tasks.get(taskNumber));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to mark as not done.");
            }
        } else if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) {
                throw new YapperException("The description of a todo cannot be empty!");
            }
            Todo newTask = new Todo(userInput.substring(5), false);
            tasks.add(newTask);
            ui.showAddedTaskMessage(newTask, tasks.size());
        } else if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split("/by");
                Deadline newTask = new Deadline(parts[0].trim(), false,
                        LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER));
                tasks.add(newTask);
                ui.showAddedTaskMessage(newTask, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new YapperException("Please provide a valid deadline task format.");
            }
        } else if (userInput.startsWith("event")) {
            try {
                String[] parts = userInput.substring(6).split("/from|/to");
                Event newTask = new Event(parts[0].trim(), false,
                        LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER),
                        LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMATTER));
                tasks.add(newTask);
                ui.showAddedTaskMessage(newTask, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new YapperException("Please provide a valid event task format.");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task removedTask = tasks.remove(taskNumber);
                ui.showRemovedTaskMessage(removedTask, tasks.size());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to delete.");
            }
        } else if(userInput.startsWith("find")) {
            String keyword = userInput.substring(5).trim();
            findTasks(keyword);
        } else if (userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                System.exit(0);
            }
        } else {
            throw new YapperException("Sorry but I don't know what you're yapping about :(");
        }
    }

    /**
     * Main method to start the Yapper application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        new Yapper(FILE_PATH, System.in).run();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks List of tasks based on user inputs.
     * @return List of tasks.
     */
    private static void printTaskList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public static List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword Keyword to search for.
     */
    private static void findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.description.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (!matchingTasks.isEmpty()) {
            ui.showMatchingTasks(matchingTasks, keyword);
        } else {
            ui.showError("No matching tasks found for keyword: " + keyword);
        }

        ui.showTaskList(tasks);
    }
}