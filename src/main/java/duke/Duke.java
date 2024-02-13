package duke;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
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
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    /**
     * Iteration 1:
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
     * Iteration 2:
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

        return "SCZL heard: " + input;
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

class Ui {
    private Scanner scanner;

    public Ui() {

        scanner = new Scanner(System.in);
    }

    public String showWelcome() {
        return "Hello! I'm SCZL\nWhat can I do for you?";
    }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showError(String message) {
        return message;
    }

    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    public String showTaskList(TaskList tasks) {//
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1)).append(".").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }

    public String readCommand() {

        return scanner.nextLine();
    }

    public void showLoadingError() {

        System.out.println("Error loading file.");
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

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

    public String formatMatchedTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

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


class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                writer.println(taskToFileString(task));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    } //

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


class Parser {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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

    private static Command createAddTodoCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(args);
    }

    private static Command createAddDeadlineCommand(String args) throws DukeException {
        // Assume parseAddDeadlineCommand is a method that parses the args and returns an AddDeadlineCommand
        return parseAddDeadlineCommand(args);
    }

    private static Command createAddEventCommand(String args) throws DukeException {
        // Similar to createAddDeadlineCommand
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
        String[] parts = commandArgs.split("/at", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Invalid event command format.");
        }
        String description = parts[0].trim();
        String at = parts[1].trim();
        String[] timeParts = at.split("-", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new DukeException("Invalid time format for event command.");
        }
        try {
            LocalDateTime startTime = LocalDateTime.parse(timeParts[0].trim(), dateTimeFormatter);
            LocalDateTime endTime = LocalDateTime.parse(timeParts[1].trim(), dateTimeFormatter);
            return new AddEventCommand(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }
}


class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    public TaskList() {

        this(new ArrayList<>());
    }

    public void addTask(Task task) {
        int initialSize = tasks.size();
        tasks.add(task);
        assert tasks.size() == initialSize + 1 : "Task list size should increase by 1";
    }

    public Task removeTask(int index) {

        return tasks.remove(index);
    }

    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index is out of bounds";
        return tasks.get(index);
    }

    public int getSize() {

        return tasks.size();
    }

    public List<Task> findTasks(String keyword) {
        List<Task> res = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                res.add(task);
            }
        }
        return res;
    }
}


enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}

class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return "[" + taskType + "]" + (isDone ? "[X] " : "[ ] ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}

class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.getDescription() + " (by: " + formatter.format(by) + ")";
    }
    public LocalDateTime getBy() {
        return by;
    }
}

class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.getDescription() + " (from: " + formatter.format(from) + " to: " + formatter.format(to) + ")";
    }
    public LocalDateTime getFrom() {
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }
}

abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}

class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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

    @Override
    public boolean isExit() {

        return false;
    }
}

class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}

class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}

class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return "Goodbye!";
    }

    @Override
    public boolean isExit() {
        return true; // Indicate that the application should exit
    }
}



class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return ui.formatTaskList(tasks);
    }
    public boolean isExit() {
        return false;
    }
}

class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
        return "Added task: " + newTodo;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.getSize());
        storage.save(tasks);
        return "Added task: " + newDeadline;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) { ////
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, start, end);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks.getSize());
        storage.save(tasks);
        return "Added task: " + newEvent;
    }

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
            return ui.formatMatchedTasks(matchedTasks); // Assuming you have implemented this method to format the list of tasks into a String.
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

