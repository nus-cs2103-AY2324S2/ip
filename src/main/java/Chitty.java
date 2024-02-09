import commands.Command;
import exceptions.InvalidInputFormatException;
import utils.Parser;
import utils.TaskList;
import utils.Ui;

/**
 * The Chitty class represents a chatbot-style application for managing tasks.
 */
public class Chitty {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a Chitty object.
     */
    Chitty() {
        taskList = TaskList.convertFileStringToTaskList();
        ui = new Ui();
    }

    /**
     * The main method to start the chatbot.
     * Initializes the application and handles user input.
     */
    public void run() {
        ui.displayGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] inputStrings = ui.readInput().split(" ", 2);
                Command command = Parser.parse(inputStrings);
                command.execute(ui, taskList);
                isExit = command.isExit();
            } catch (InvalidInputFormatException exception) {
                ui.displayInvalidInputMessage(exception.getMessage() + "\n");
            } catch (IllegalArgumentException exception) {
                ui.displayInvalidCommandMessage();
            } catch (Exception exception) {
                ui.displayInvalidInputMessage();
            }
        }
    }

    /**
     * The main method to start the application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Chitty().run();
    }
}
