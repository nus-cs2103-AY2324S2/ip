package harper;

import harper.commands.Command;

import harper.exceptions.HarperException;

import harper.utils.Parser;
import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * A chatbot called Harper.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String folderName, String fileName) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(folderName, fileName);
            this.taskList = new TaskList(this.storage.load());
        } catch (HarperException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (HarperException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data", "harper.txt");
        duke.run();
    }
}
