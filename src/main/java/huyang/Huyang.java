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
    /**
     * Storage for handling the saving and loading of tasks from a file.
     * It abstracts the details of how tasks are persisted in local storage.
     */
    private Storage storage;

    /**
     * TaskList for managing the in-memory list of tasks.
     * It provides operations such as add, delete, and search tasks within the list.
     */
    private TaskList tasks;

    /**
     * Ui for handling user interactions.
     * It manages input reading and output formatting to present information to the user.
     */
    private Ui ui;

    /**
     * Parser for interpreting user commands.
     * It translates user input into commands that can be executed by the application.
     */
    private Parser parser;

    /**
     * Constructor for the Huyang class.
     * Initializes the user interface, parser, and storage, and loads tasks from a file.
     */
    public Huyang() {
        this.parser = new Parser();
        String relativePath = "data/huyang_tasks.txt";
        this.storage = new Storage(relativePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException | TaskException e) {
            Ui.print(Ui.getErrorMessage("Error initializing tasks: " + e.getMessage()));
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
                return Ui.getTasksMessage(tasks.getTasks());
            case MARK:
                Task markedTask = tasks.markOrUnmarkTask(input, true);
                storage.saveTasks(tasks.getTasks());
                return Ui.getMarkOrUnmarkMessage(markedTask, true);
            case UNMARK:
                Task unmarkedTask = tasks.markOrUnmarkTask(input, false);
                storage.saveTasks(tasks.getTasks());
                return Ui.getMarkOrUnmarkMessage(unmarkedTask, false);
            case TODO:
            case DEADLINE:
            case EVENT:
                Task addedTask = tasks.addTask(input, command);
                storage.saveTasks(tasks.getTasks());
                return Ui.getAddTaskMessage(addedTask, tasks.getSize());
            case DELETE:
                Task deletedTask = tasks.deleteTask(input);
                storage.saveTasks(tasks.getTasks());
                return Ui.getDeleteTaskMessage(deletedTask, tasks.getSize());
            case FIND:
                String keyword = input.substring(5).trim().toLowerCase();
                ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                return Ui.getFoundTasksMessage(foundTasks);
            case BYE:
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
                return Ui.getFarewellMessage();
            case UNKNOWN:
            default:
                return Ui.getUnknownCommandMessage();
            }
        } catch (TaskException | IOException e) {
            return Ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Starts the Huyang chatbot.
     * Displays a greeting, processes user commands, and saves tasks to a file.
     * Supports other features such as list, mark, unmark and delete.
     */
    public void runCli() {
        Ui.print(Ui.getGreetingMessage());
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine();
            Parser.CommandType command = parser.parseCommand(input);

            try {
                switch (command) {
                case LIST:
                    Ui.print(Ui.getTasksMessage(tasks.getTasks()));
                    break;
                case MARK:
                    Task markedTask = tasks.markOrUnmarkTask(input, true);
                    Ui.print(Ui.getMarkOrUnmarkMessage(markedTask, true));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case UNMARK:
                    Task unmarkedTask = tasks.markOrUnmarkTask(input, false);
                    Ui.print(Ui.getMarkOrUnmarkMessage(unmarkedTask, false));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task addedTask = tasks.addTask(input, command);
                    Ui.print(Ui.getAddTaskMessage(addedTask, tasks.getSize()));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case DELETE:
                    Task deletedTask = tasks.deleteTask(input);
                    Ui.print(Ui.getDeleteTaskMessage(deletedTask, tasks.getSize()));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case FIND:
                    String keyword = input.substring(5).trim().toLowerCase();
                    ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                    Ui.print(Ui.getFoundTasksMessage(foundTasks));
                    break;
                case BYE:
                    isExit = true;
                    break;
                case UNKNOWN:
                default:
                    Ui.print(Ui.getUnknownCommandMessage());
                    break;
                }
            } catch (TaskException | IOException e) {
                Ui.print(Ui.getErrorMessage(e.getMessage()));
            }
        }
        scanner.close();
        Ui.print(Ui.getFarewellMessage());
        Ui.print("Success");
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