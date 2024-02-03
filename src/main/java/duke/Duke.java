package duke;

import duke.command.Command;

/**
 * The `Duke` class is the entrance of the program.
 * It provides methods to run the program as a chatbot.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes a Duke program.
     *
     * @param filePath A string representing the file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs a Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry function of the Duke program.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
