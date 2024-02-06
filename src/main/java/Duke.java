/**
 * Represents a chatbot that assists the user with managing a task list.
 * This chatbot has a task-list to keep track of tasks and a storage to store task content.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.breakLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.breakLine();
                }
            }
        }
        ui.exit();
    }

    /**
     * Starts the chatbot and scans for user input until the user says bye.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
