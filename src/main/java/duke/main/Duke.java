package duke.main;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Represents a ChatBot class.
 */
public class Duke {
    TaskList taskList;
    private String filePath = "./taskList.txt";
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for the ChatBot class.
     */
    public Duke() {
        this.storage = new Storage(this.filePath);
        this.taskList = this.storage.load();
        this.ui = new Ui();
    }

    /**
     * Run the ChatBot to interact with user.
     */
    public void run() {
        this.ui.sayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand, this.ui, this.taskList, this.storage);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Return response from the Ui to the user.
     *
     * @return String Message from the Ui.
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput, this.ui, this.taskList, this.storage);
            String response = c.execute();
            return response;
        } catch (DukeException e) {
            return this.ui.printErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
