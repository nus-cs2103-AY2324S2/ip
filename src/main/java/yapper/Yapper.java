package yapper;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.TaskList;
import yapper.tasks.Todo;


/**
 * The Yapper class is the main class that handles user input and manages tasks.
 * It includes methods for processing user input, displaying messages, and interacting with storage.
 */
public class Yapper {
    private static final String FILE_PATH = "./src/main/java/data/yapper.Yapper.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private List<Task> tasks;
    private Ui ui;
    private Scanner userScanner;
    private Storage storage;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private volatile boolean isRunning = true;
    private MainWindow mainWindow;

    public Ui getUi() {
        return ui;
    }

    public void start() {
        // Start the application loop in a separate thread
        Thread yapperThread = new Thread(this::run);
        yapperThread.start();
    }

    public void stop() {
        // Set the termination condition to stop the application loop
        isRunning = false;
    }

    /**
     * Constructs a new Yapper instance.
     *
     * @param filePath    The file path for task data.
     * @param inputStream The input stream for user input.
     */
    public Yapper(String filePath, InputStream inputStream) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YapperException e) {
            this.ui.showLoadingError();
            tasks = new TaskList();
        }
        userScanner = new Scanner(inputStream);
    }

    /**
     * Runs the Yapper application, displaying welcome messages and handling user input.
     */
    public void run() {
        this.ui.showWelcomeMessage();

        try {
            while (isRunning) {
                this.ui.showUserPrompt();
                String userInput = userScanner.nextLine();
                processUserInput(userInput);
                this.storage.saveTasks(this.tasks);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            this.ui.showError("No input provided.");
        } catch (YapperException e) {
            this.ui.showError(e.getMessage());
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
    public String processUserInput(String userInput) throws YapperException {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            if (userInput.equalsIgnoreCase("list")) {
                responseBuilder.append(this.ui.showTaskList(this.tasks));
            } else if (userInput.startsWith("mark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(taskNumber).markAsDone();
                    responseBuilder.append(this.ui.showMarkedDoneMessage(this.tasks.get(taskNumber)));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new YapperException("Please provide a valid task number to mark as done.");
                }
            } else if (userInput.startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(taskNumber).markAsNotDone();
                    responseBuilder.append(this.ui.showMarkedNotDoneMessage(this.tasks.get(taskNumber)));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new YapperException("Please provide a valid task number to mark as not done.");
                }
            } else if (userInput.startsWith("todo")) {
                if (userInput.length() <= 5) {
                    throw new YapperException("The description of a todo cannot be empty!");
                }
                Todo newTask = new Todo(userInput.substring(5), false);
                this.tasks.add(newTask);
                responseBuilder.append(this.ui.showAddedTaskMessage(newTask, this.tasks.size()));
            } else if (userInput.startsWith("deadline")) {
                try {
                    String[] parts = userInput.substring(9).split("/by");
                    Deadline newTask = new Deadline(parts[0].trim(), false,
                            LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER));
                    this.tasks.add(newTask);
                    responseBuilder.append(this.ui.showAddedTaskMessage(newTask, this.tasks.size()));
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
                    responseBuilder.append(this.ui.showAddedTaskMessage(newTask, this.tasks.size()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new YapperException("Please provide a valid event task format.");
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task removedTask = this.tasks.remove(taskNumber);
                    responseBuilder.append(this.ui.showRemovedTaskMessage(removedTask, this.tasks.size()));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new YapperException("Please provide a valid task number to delete.");
                }
            } else if (userInput.startsWith("find")) {
                String keyword = userInput.substring(5).trim();
                findTasks(keyword);
            } else if (userInput.equalsIgnoreCase("bye")) {
                responseBuilder.append(this.ui.showGoodbyeMessage());
                System.exit(0);
            } else {
                throw new YapperException("Sorry but I don't know what you're yapping about :(");
            }
        } catch (YapperException e) {
            responseBuilder.append(this.ui.showError(e.toString()));
        }
        return responseBuilder.toString();
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
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword Keyword to search for.
     */
    private void findTasks(String keyword) {
        List<Task> matchingTasks = this.tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (!matchingTasks.isEmpty()) {
            this.ui.showMatchingTasks(matchingTasks, keyword);
        } else {
            this.ui.showError("No matching tasks found for keyword: " + keyword);
        }

        this.ui.showTaskList(this.tasks);
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public String showWelcomeMessage() {
        String message = "Hello! I'm Yapper. \n"
                + "What would you like to yap about today? :-)";
        System.out.println(message);
        return message;
    }
}