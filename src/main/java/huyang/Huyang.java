package huyang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * The main class for the Huyang CLI chatbot that allows user
 * input to keep track of tasks.
 */
public class Huyang {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Huyang class.
     * Initializes the user interface, parser, and storage, and loads tasks from a file.
     */
    public Huyang() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(System.getProperty("user.home")
                + "/Downloads/CS2103T/ip/data/huyang_tasks.txt");
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException | TaskException e) {
            ui.print(ui.getErrorMessage("Error initializing tasks: " + e.getMessage()));
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Processes user commands and saves tasks to local storage. Generates a response based on the given command.
     *
     * @param input The user's input command.
     * @return A response message generated based on the input.
     */
    public String getResponse(String input) {
        Parser.CommandType command = parser.parseCommand(input);
        try {
            switch (command) {
            case LIST:
                return ui.getTasksMessage(tasks.getTasks());
            case MARK:
                Task markedTask = tasks.markOrUnmarkTask(input, true);
                storage.saveTasks(tasks.getTasks());
                return ui.getMarkOrUnmarkMessage(markedTask, true);
            case UNMARK:
                Task unmarkedTask = tasks.markOrUnmarkTask(input, false);
                storage.saveTasks(tasks.getTasks());
                return ui.getMarkOrUnmarkMessage(unmarkedTask, false);
            case TODO:
            case DEADLINE:
            case EVENT:
                Task addedTask = tasks.addTask(input, command);
                storage.saveTasks(tasks.getTasks());
                return ui.getAddTaskMessage(addedTask, tasks.getSize());
            case DELETE:
                Task deletedTask = tasks.deleteTask(input);
                storage.saveTasks(tasks.getTasks());
                return ui.getDeleteTaskMessage(deletedTask, tasks.getSize());
            case FIND:
                String keyword = input.substring(5).trim().toLowerCase();
                ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                return ui.getFoundTasksMessage(foundTasks);
            case BYE:
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
                return ui.getFarewellMessage();
            case UNKNOWN:
            default:
                return ui.getUnknownCommandMessage();
            }
        } catch (TaskException | IOException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Starts the Huyang chatbot.
     * Displays a greeting, processes user commands, and saves tasks to a file.
     * Supports other features such as list, mark, unmark and delete.
     */
    public void runCli() {
        ui.print(Ui.getGreetingMessage());
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine();
            Parser.CommandType command = parser.parseCommand(input);

            try {
                switch (command) {
                case LIST:
                    ui.print(ui.getTasksMessage(tasks.getTasks()));
                    break;
                case MARK:
                    Task markedTask = tasks.markOrUnmarkTask(input, true);
                    ui.print(ui.getMarkOrUnmarkMessage(markedTask, true));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case UNMARK:
                    Task unmarkedTask = tasks.markOrUnmarkTask(input, false);
                    ui.print(ui.getMarkOrUnmarkMessage(unmarkedTask, false));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task addedTask = tasks.addTask(input, command);
                    ui.print(ui.getAddTaskMessage(addedTask, tasks.getSize()));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case DELETE:
                    Task deletedTask = tasks.deleteTask(input);
                    ui.print(ui.getDeleteTaskMessage(deletedTask, tasks.getSize()));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case FIND:
                    String keyword = input.substring(5).trim().toLowerCase();
                    ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                    ui.print(ui.getFoundTasksMessage(foundTasks));
                    break;
                case BYE:
                    isExit = true;
                    break;
                case UNKNOWN:
                default:
                    ui.print(ui.getUnknownCommandMessage());
                    break;
                }
            } catch (TaskException | IOException e) {
                ui.print(ui.getErrorMessage(e.getMessage()));
            }
        }
        scanner.close();
        ui.print(Ui.getFarewellMessage());
        ui.print("Success");
    }

    /**
     * Main method to start the Huyang chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Huyang().runCli();
        System.exit(0);
    }
}