package huyang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        this.storage = new Storage("./data/huyang_tasks.txt");
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException | TaskException e) {
            ui.printErrorMessage("Error initializing tasks: " + e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }
    /**
     * Starts the Huyang chatbot.
     * Displays a greeting, processes user commands, and saves tasks to a file.
     * Supports other features such as list, mark, unmark and delete.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Parser.CommandType command = parser.parseCommand(input);
            boolean isChanged = false;

            try {
                switch (command) {
                    case LIST:
                        ui.showTasks(tasks.getTasks());
                        break;
                    case MARK:
                    case UNMARK:
                        isChanged = tasks.markOrUnmarkTask(input, command, ui);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        isChanged = tasks.addTask(input, command, ui);
                        break;
                    case DELETE:
                        isChanged = tasks.deleteTask(input, ui);
                        break;
                    case FIND:
                        String keyword = input.substring(5).trim().toLowerCase();
                        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                        ui.showFoundTasks(foundTasks);
                        break;
                    case BYE:
                        isExit = true;
                        break;
                    case UNKNOWN:
                    default:
                        ui.printUnknownCommandMessage();
                        break;
                }
            } catch (TaskException e) {
                ui.printErrorMessage(e.getMessage());
            }

            if (isChanged) {
                try {
                    storage.saveTasks(tasks.getTasks());
                } catch (IOException | TaskException e) {
                    ui.printErrorMessage("Error saving tasks: " + e.getMessage());
                }
            }
        }

        scanner.close();
        ui.farewell();
    }

    /**
     * Main method to start the Huyang chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Huyang().run();
    }
}