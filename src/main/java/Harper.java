import exceptions.HarperException;

/**
 * A chatbot called Harper.
 *
 * @author gosongying
 * @version CS2103T AY23/24 Sem 2
 */
public class Harper {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Harper(String folderName, String fileName) {
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
     * Starts the chat bot.
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
}
