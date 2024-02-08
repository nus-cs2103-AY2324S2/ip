package alastor;

import alastor.command.Command;

/**
 * Represents Alastor, a personal assistant chatBot that helps a person to keep track of various things.
 */
public class Alastor {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an Alastor object.
     *
     * @param filePath The file path to save and load tasks from.
     */
    public Alastor(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AlastorException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Alastor program.
     */
    public void run() {
        ui.showLine();
        ui.showGreet();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlastorException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Alastor("./data/tasks.txt").run();
    }
}
