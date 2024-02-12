import command.Command;
import exceptions.DukeException;
import task.TaskList;
import ui.Ui;
import utilities.Parser;
import utilities.Storage;

/**
 * Duke class responsible for running the program.
 */
public class Duke {
    /**
     * Ui responsible for user interaction.
     */
    private Ui ui;
    /**
     * The user's task list.
     */
    private TaskList taskList;
    /**
     * The storage used to access the save file if it exists.
     */
    private Storage storage;

    /**
     * Duke class constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("duke.txt", "./src/main/data");
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Method that generates a response from the chatbot.
     * @param userInput The input the user keys in.
     * @return The message the chatbot replies.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            return command.execute(taskList, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        ui.showWelcome();
        return ui.getOutput();
    }
}
