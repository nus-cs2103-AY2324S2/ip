package bob;

import bob.command.Command;
import bob.exception.BobException;

public class Bob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    private void runCommandLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(ui, storage, tasks);
                isExit = command.checkExit();
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

    public static void main(String[] args) {
        new Bob(Storage.DATA_PATH).run();
    }
}
