package henry;

import henry.command.Command;

import java.io.IOException;

public class Henry {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Represents a Henry chatbot that stores and manages tasks.
     *
     * @param filePath The file path to store the tasks.
     */
    public Henry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | HenryException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HenryException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Henry("data/tasks.txt").run();
    }
}
