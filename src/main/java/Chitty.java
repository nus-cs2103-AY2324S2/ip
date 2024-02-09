import commands.Command;
import exceptions.InvalidInputFormatException;
import helpers.Parser;
import helpers.TaskList;
import helpers.Ui;

/**
 * Chatbot-style application for managing tasks.
 */
public class Chitty {
    private final TaskList taskList;
    private final Ui ui;

    Chitty() {
        this.taskList = TaskList.convertFileStringToTaskList();
        this.ui = new Ui();
    }

    /**
     * The main method to start the chatbot. Initializes the application and handles user input.
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

    public static void main(String[] args) {
        new Chitty().run();
    }
}
