package duke;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Represents the main class for the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String FILE_PATH = "./data/duke.txt/duke.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/minnie.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/spinminnie.jpeg"));
    /**
     * Constructs a new Duke instance with the specified file path for data storage.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        initializeStage(stage);
        setupSceneAndComponents();
        configureStage(stage);
        addInputHandlers();
    }

    /**
     * Initializes the stage with the main window scene.
     *
     * @param stage The primary stage for this application.
     */
    private void initializeStage(Stage stage) {
        Duke duke = new Duke();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this, tasks, ui, storage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the scene and its components including the scroll pane, dialog container, user input, and send button.
     */
    private void setupSceneAndComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
    }

    /**
     * Configures the stage with title, size, and layout preferences.
     *
     * @param stage The primary stage to be configured.
     */
    private void configureStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        AnchorPane mainLayout = (AnchorPane) scene.getRoot();
        mainLayout.setPrefSize(400.0, 600.0);
        configureScrollPane();
        configureInputAndButton();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Configures the scroll pane and dialog container for proper layout and behavior.
     */
    private void configureScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Configures the user input and send button, including their positions and sizes.
     */
    private void configureInputAndButton() {
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane mainLayout = (AnchorPane) scene.getRoot();
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Adds event handlers for the send button and user input field to handle user interactions.
     */
    private void addInputHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInputText = userInput.getText();
        String response;

        try {
            Command command = Parser.parse(userInputText);
            command.execute(tasks, ui, storage);
            if (command instanceof DeleteCommand || command instanceof AddTodoCommand || command instanceof AddEventCommand || command instanceof AddDeadlineCommand || command instanceof ListCommand || command instanceof MarkCommand || command instanceof UnmarkCommand) {
                // Show updated task list after adding/deleting a task or when list command is invoked
                response = ui.showTaskList(tasks);
            } else if (command instanceof FindCommand) {
                response = command.execute(tasks, ui, storage);
            } else {
                // For other commands, you can customize the response
                response = "Command executed successfully.";
            }
        } catch (DukeException e) {
            // Handle exceptions, for example, invalid command, task not found, etc.
            response = e.getMessage();
        }

        // Display the response or updated task list in the GUI
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputText, user),
                DialogBox.getDukeDialog(response, duke)
        );

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "NyanTasks heard: " + input;
    }

    /**
     * Runs the Duke application. Initializes the necessary components and starts
     * the command loop to receive and process user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit(); // Check if the command signals to exit
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner();
    }
    /**
     * The entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(new String[2]);
    }
}


/**
 * A launcher class to workaround classpath issues.
 */
class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}


class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
/**
 * This class handles the user interface part of the application.
 * It is responsible for all user interactions, including displaying messages to the user
 * and reading user input.
 */
class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui instance. Initializes the scanner used to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return A welcome message string.
     */
    public String showWelcome() {
        return "Hello! I'm NyanTasks\nWhat can I do for you?";
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return A goodbye message string.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     * @return The error message string.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that has been added.
     * @param taskCount The total number of tasks in the list after adding.
     * @return A message string indicating the task has been added and the current task count.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @return A string representing the list of tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1)).append(".").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading file.");
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param taskCount The total number of tasks remaining in the list.
     */
    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a list of tasks that match the search criteria.
     *
     * @param matchedTasks The list of tasks that matched the search.
     * @return A string representation of the matching tasks list.
     */
    public String showMatchedTasks(List<Task> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                response.append(i + 1).append(".").append(matchedTasks.get(i)).append("\n");
            }
            return response.toString();
        }
    }

    /**
     * Formats a list of tasks into a string.
     *
     * @param tasks The list of tasks to be formatted.
     * @return A string representation of the tasks list.
     */
    public String formatMatchedTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Formats the task list into a string.
     *
     * @param tasks The TaskList to be formatted.
     * @return A string representation of the task list.
     */
    public String formatTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "Task list is empty.";
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            response.append(i + 1).append(".").append(task).append("\n");
        }
        return response.toString();
    }
}


/**
 * Handles storage operations for Duke application, including loading from and saving tasks to a file.
 */
class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    /**
     * Ensures that the storage file exists. If it does not, the method attempts to create it.
     */
    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            File parentDirectory = file.getParentFile();
            if (parentDirectory != null && !parentDirectory.exists()) {
                parentDirectory.mkdirs(); // Create the directory if it doesn't exist
            }
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("An error occurred while ensuring the data file exists: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If the file cannot be read.
     */
    public List<Task> load() throws DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new DukeException("File not found");
        }

        try {
            return Files.lines(Paths.get(filePath))
                    .map(this::parseLineToTask)
                    .filter(java.util.Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new DukeException("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     *
     * @param line The line to be parsed.
     * @return The Task object created from the line, or null if the task is invalid.
     */
    private Task parseLineToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task = createTask(type, description, parts);
            if (isDone) task.markAsDone();
            return task;
        } catch (DukeException | DateTimeParseException e) {
            System.out.println("Skipping invalid task: " + line);
            return null;
        }
    }

    /**
     * Creates a Task object based on the type and description provided.
     *
     * @param type The type of the task (e.g., "T" for todo).
     * @param description The description of the task.
     * @param parts An array containing additional information needed to create the task.
     * @return The created Task object.
     * @throws DukeException If the task type is unknown or format is invalid.
     */
    private Task createTask(String type, String description, String[] parts) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch (type) {
            case "T":
                return new Todo(description);
            case "D":
                if (parts.length < 4) throw new DukeException("Invalid deadline format in file.");
                LocalDateTime byDate = LocalDateTime.parse(parts[3], formatter);
                return new Deadline(description, byDate);
            case "E":
                if (parts.length < 5) throw new DukeException("Invalid event format in file.");
                LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                return new Event(description, from, to);
            default:
                throw new DukeException("Unknown task type: " + type);
        }
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                writer.println(taskToFileString(task));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object into a string representation suitable for storage.
     *
     * @param task The task to be converted.
     * @return The string representation of the task.
     */
    private String taskToFileString(Task task) {
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        String status = task.isDone ? "1" : "0";
        String details = task.getDescription();
        String additionalInfo = "";

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            additionalInfo = " | " + deadline.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            additionalInfo = " | " + event.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                    " | " + event.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        return type + " | " + status + " | " + details + additionalInfo;
    }
}

/**
 * This class is responsible for parsing user input into command objects for execution.
 */
class Parser {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the user input into a command object.
     *
     * @param fullCommand The full string input by the user.
     * @return The command object corresponding to the user input.
     * @throws DukeException If the command is unknown or if there is a format issue with the input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandType = commandParts[0].toLowerCase(); // Consider case-insensitivity
        String commandArgs = commandParts.length > 1 ? commandParts[1] : "";

        switch (commandType) {
            case "todo":
                return createAddTodoCommand(commandArgs);
            case "deadline":
                return createAddDeadlineCommand(commandArgs);
            case "event":
                return createAddEventCommand(commandArgs);
            case "list":
                return new ListCommand();
            case "mark":
                return createMarkCommand(commandArgs);
            case "unmark":
                return createUnmarkCommand(commandArgs);
            case "delete":
                return createDeleteCommand(commandArgs);
            case "find":
                return createFindCommand(commandArgs);
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("Unknown command");
        }
    }

    /**
     * Creates an AddTodoCommand with the given argument.
     *
     * @param args The argument for the todo command, which is the description of the task.
     * @return An instance of AddTodoCommand.
     * @throws DukeException If the description is empty.
     */
    private static Command createAddTodoCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(args);
    }

    private static Command createAddDeadlineCommand(String args) throws DukeException {
        return parseAddDeadlineCommand(args);
    }

    private static Command createAddEventCommand(String args) throws DukeException {
        return parseAddEventCommand(args);
    }

    private static Command createMarkCommand(String args) throws DukeException {
        int index = parseIndex(args);
        return new MarkCommand(index);
    }

    private static Command createUnmarkCommand(String args) throws DukeException {
        int index = parseIndex(args);
        return new UnmarkCommand(index);
    }

    private static Command createDeleteCommand(String args) throws DukeException {
        int index = parseIndex(args);
        return new DeleteCommand(index);
    }

    private static Command createFindCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The keyword for find cannot be empty.");
        }
        return new FindCommand(args);
    }

    /**
     * Parses an index from the command arguments.
     *
     * @param args The command arguments containing the index.
     * @return The parsed index.
     * @throws DukeException If the index is not a valid integer.
     */
    private static int parseIndex(String args) throws DukeException {
        try {
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number format.");
        }
    }

    private static Command parseAddDeadlineCommand(String commandArgs) throws DukeException {
        String[] parts = commandArgs.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Invalid deadline command format.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        try {
            LocalDateTime byDate = LocalDateTime.parse(by, dateTimeFormatter);
            return new AddDeadlineCommand(description, byDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }

    private static Command parseAddEventCommand(String commandArgs) throws DukeException {
        String[] parts = commandArgs.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid event command format. Missing '/from'.");
        }
        String description = parts[0].trim();

        String[] timeParts = parts[1].trim().split("/to", 2);
        if (timeParts.length < 2) {
            throw new DukeException("Invalid event command format. Missing '/to'.");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(from, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(to, formatter);
            return new AddEventCommand(description, startDateTime, endDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date and time format. Please use 'MMM dd yyyy, HH:mm' format.");
        }
    }
}


/**
 * Represents a list of tasks in the Duke application.
 * Provides functionality to add, remove, and query tasks.
 */
class TaskList {
    private List<Task> tasks;

    /**
     * Creates a TaskList with the specified list of tasks.
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        int initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "Task list size should increase by 1";
    }

    /**
     * Removes a task from the list by index.
     * @param index The index of the task to be removed.
     * @return The removed Task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list by index.
     * @param index The index of the task to retrieve.
     * @return The Task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index is out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the keyword search.
     */
    public List<Task> findTasks(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());
    }
}



enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}

/**
 * Represents a general task with a description, completion status, and type.
 */
class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a new Task with the specified description and type.
     * Initially, the task is not completed.
     *
     * @param description Description of the task.
     * @param taskType Type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return "[" + taskType + "]" + (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}

/**
 * Represents a TODO task with a description.
 */
class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description Description of the TODO task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}


/**
 * Represents a Deadline task with a description and a deadline date.
 */
class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     *
     * @param description Description of the deadline task.
     * @param by The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns the description of the deadline task including the deadline date.
     *
     * @return Description of the task with its deadline.
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.getDescription() + " (by: " + formatter.format(by) + ")";
    }

    /**
     * Gets the deadline date and time of the task.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getBy() {
        return by;
    }
}

/**
 * Represents an Event task with a description, start date/time, and end date/time.
 */
class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start date/time, and end date/time.
     *
     * @param description Description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the description of the event including its start and end date/time.
     *
     * @return Description of the event with its start and end timings.
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.getDescription() + " (from: " + formatter.format(from) + " to: " + formatter.format(to) + ")";
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return The start date and time.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end date and time of the event.
     *
     * @return The end date and time.
     */
    public LocalDateTime getTo() {
        return to;
    }
}


abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}

/**
 * Represents a command to mark a task as done.
 */
class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand to mark the task at the specified index as done.
     *
     * @param index The index of the task in the task list to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking the specified task as done,
     * showing the marked task, and saving the updated task list to storage.
     *
     * @param tasks The task list containing the task to be marked.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     * @return A string indicating the task has been marked as done.
     * @throws DukeException If the specified index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.showMarkedTask(task);
        storage.save(tasks);
        return "Marked as done: " + task;
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false, indicating the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to unmark a task as not done.
 */
class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand to unmark the task at the specified index as not done.
     *
     * @param index The index of the task in the task list to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, unmarking the specified task as not done,
     * showing the unmarked task, and saving the updated task list to storage.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     * @return A string indicating the task has been unmarked as not done.
     * @throws DukeException If the specified index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        ui.showUnmarkedTask(task);
        storage.save(tasks);
        return "Marked as not done: " + task;
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false, indicating the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to delete a task from the task list.
 */
class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand to delete the task at the specified index from the task list.
     *
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command, removing the specified task from the task list,
     * showing the deleted task, and saving the updated task list to storage.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     * @return A string indicating the task has been deleted.
     * @throws DukeException If the specified index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.removeTask(index);
        ui.showDeletedTask(task, tasks.getSize());
        storage.save(tasks);
        return "Deleted task: " + task;
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false, indicating the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to exit the application.
 */
class ExitCommand extends Command {
    /**
     * Executes the exit command, showing a goodbye message.
     *
     * @param tasks The current task list (not used in this command).
     * @param ui The user interface for displaying the goodbye message.
     * @param storage The storage (not used in this command).
     * @return A string indicating that the application will exit.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return "Goodbye!";
    }

    /**
     * Indicates that this command causes the application to exit.
     *
     * @return true, indicating the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

/**
 * Represents a command to list all tasks currently in the task list.
 */
class ListCommand extends Command {
    /**
     * Executes the command to display all tasks in the task list.
     *
     * @param tasks The task list to be displayed.
     * @param ui The user interface to display the task list.
     * @param storage The storage of the task list (not directly used in this command).
     * @return A formatted string representing all tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return ui.formatTaskList(tasks);
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return false, indicating that the command does not exit the application.
     */
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to add a new Todo task to the task list.
 */
class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param description The description of the Todo task to be added.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a new Todo task to the task list, shows the added task,
     * and saves the updated task list to storage.
     *
     * @param tasks The task list where the new Todo will be added.
     * @param ui The user interface to display the added task message.
     * @param storage The storage to save the updated task list.
     * @return A string indicating the new Todo task has been added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
        return "Added task: " + newTodo;
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false, indicating that the command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to add a new Deadline task to the task list.
 */
class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Constructs an AddDeadlineCommand with the specified task description and deadline.
     *
     * @param description The description of the Deadline task to be added.
     * @param by The deadline by which the task needs to be completed.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a new Deadline task to the task list, shows the added task,
     * and saves the updated task list to storage.
     *
     * @param tasks The task list where the new Deadline will be added.
     * @param ui The user interface to display the added task message.
     * @param storage The storage to save the updated task list.
     * @return A string indicating the new Deadline task has been added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.getSize());
        storage.save(tasks);
        return "Added task: " + newDeadline;
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false, indicating that the command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * Represents a command to add a new Event task to the task list.
 */
class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an AddEventCommand with the specified task description, start time, and end time.
     *
     * @param description The description of the Event task to be added.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command to add a new Event task to the task list, shows the added task,
     * and saves the updated task list to storage.
     *
     * @param tasks The task list where the new Event will be added.
     * @param ui The user interface to display the added task message.
     * @param storage The storage to save the updated task list.
     * @return A string indicating the new Event task has been added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, start, end);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks.getSize());
        storage.save(tasks);
        return "Added task: " + newEvent;
    }

    /**
     * Indicates that this command does not cause the application to exit.
     *
     * @return false, indicating that the command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

class FindCommand extends Command {
    private String keyword;
    private String test;

    public FindCommand(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword for find command cannot be empty";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.isEmpty()) {
            return "No tasks matched your keyword.";
        } else {
            return ui.formatMatchedTasks(matchedTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String getKeyword() {
        return keyword;
    }
}