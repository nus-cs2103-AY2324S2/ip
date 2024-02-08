package liv;

import liv.processor.Command;
import liv.container.Storage;
import liv.exception.LivException;
import liv.processor.Parser;
import liv.container.TaskList;
import liv.ui.Ui;

public class Liv {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Liv(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() {
        ui.displayGreetCommand();
        storage.loadDataFile();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.displayBar(); // show the divider line ("_______")
                Command c = Parser.parse(input);
                c.execute(tasks, ui);
                if (c.hasChangedData()) {
                    storage.saveTaskToFile();
                }
                isExit = c.isExit();
            } catch (LivException e) {
                ui.displayErrorMessage(e.getMessage());
            } finally {
                ui.displayBar();
            }
        }
    }
    public static void main(String[] args) {
        new Liv("data/liv.Liv.txt").run();
    }
}
