package bob;

import bob.command.Command;
import bob.exception.BobException;

/**
 * Represents Bob itself. A <code>Bob</code> object corresponds to an instance of the program.
 */
public class Bob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Returns an instance of the program with its own storage, task list and UI.
     *
     * @param dataPath The file path of the storage.
     */
    public Bob(String dataPath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load(dataPath));
        } catch (BobException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Reads and executes the command given by the user until an exit command is encountered.
     */
    private void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(ui, storage, tasks);
                isExit = command.isExit();
            } catch (BobException e) {
                ui.show(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private void run() {
        ui.showWelcome();
        runCommandLoopUntilExitCommand();
    }

    /**
     * The entry point for the program.
     *
     * @param args The arguments passed to the <code>main</code> function.
     */
    public static void main(String[] args) {
        new Bob(Storage.DATA_PATH).run();
    }
}
