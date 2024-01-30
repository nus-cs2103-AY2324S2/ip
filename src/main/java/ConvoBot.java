import commands.Command;
import exceptions.ConvoBotException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.UI;

/**
 * The main class representing the ConvoBot application.
 */
public class ConvoBot {

    private final TaskList tasks;
    private final UI ui;

    /**
     * Constructor for ConvoBot class.
     *
     * @param filePath The file path for task data storage.
     */
    public ConvoBot(String filePath) {
        tasks = new TaskList(new Storage(filePath));
        ui = new UI();
    }

    /**
     * Main execution loop for the ConvoBot application.
     * Shows welcome message, reads user input, parses and executes commands until the exit command is encountered.
     * Handles exceptions and displays error messages.
     */
    public void run() {
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
     * Main entry point for the ConvoBot application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new ConvoBot("./data/tasks.txt").run();
    }
}
