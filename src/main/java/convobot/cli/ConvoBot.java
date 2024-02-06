package cli;

import javafx.application.Application;

import commands.Command;
import exceptions.ConvoBotException;
import gui.Main;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

/**
 * The main class representing the ConvoBot application.
 */
public class ConvoBot {

    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for ConvoBot class.
     *
     * @param filePath The file path for task data storage.
     */
    public ConvoBot(String filePath) {
        tasks = new TaskList(new Storage(filePath));
        ui = new Ui();
    }

    /**
     * Main execution loop for the ConvoBot command-line application.
     * Shows welcome message, reads user input, parses and executes commands until the exit command is encountered.
     * Handles exceptions and displays error messages.
     */
    private void run() {
        ui.showWelcomeMsg();
        while (true) {
            String userInput = ui.readUserInput();
            Command c;
            try {
                c = Parser.parseUserInput(userInput);
                if (c.isExit()) {
                    break;
                }
                ui.showHorizontalLine(false);
            } catch (ConvoBotException e) {
                ui.showHorizontalLine(false);
                ui.showError(e.getMessage());
                ui.showHorizontalLine(true);
                continue;
            }
            try {
                c.execute(tasks, ui);
            } catch (ConvoBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showHorizontalLine(true);
            }
        }
        ui.showExitMsg();
    }

    /**
     * Retrieves a response from ConvoBot based on the provided input.
     *
     * @param input The input string representing the user's message.
     * @return A string containing ConvoBot's response.
     */
    public String getResponse(String input) {
        return "ConvoBot heard: " + input;
    }

    /**
     * Main entry point for the ConvoBot application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // new ConvoBot("./data/tasks.txt").run(); // uncomment this to use CLI instead of GUI
        Application.launch(Main.class, args);
    }
}
