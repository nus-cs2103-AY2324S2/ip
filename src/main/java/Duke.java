import java.io.IOException;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private TaskList tasks;
    private final Ui ui;

    public Duke() {
        ui = new Ui();
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
