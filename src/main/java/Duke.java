package main;

import commands.Command;
import commands.Parser;

import exceptions.DukeException;

import tasks.TaskList;

import ui.Ui;

/**
 * Duke is the main class for the task management application.
 * It initializes the application and starts the interaction loop with the user.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Duke object.
     * Initializes the UI, storage, and task list components of the application.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.greet();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
        run();

    }

    public Duke() {}

    /**
     * Starts the application and enters the command processing loop.
     * The loop reads commands from the user, parses them, and executes them
     * until the user issues the bye command.
     */
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            try {
                Command c = Parser.parse(userInput);
                c.execute(taskList, ui, storage, userInput);
                if (c.equals(Command.BYE)) {
                    break;
                }
            } catch (DukeException e) {
            System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
            System.out.println("Quit yappin, that task does not exist");
        }


        }
        ui.exit();
    }

    /**
     * The main entry point for the application.
     * Creates a new Duke instance and starts the application.
     *
     * @param args Command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        new Duke("./data/taskyapper.txt");

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
