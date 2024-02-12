// TODO: divide into multiple packages
package bob;

public class Bob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Bob() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
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

    public static void main(String[] args) {
        new Bob().run();
    }
}
