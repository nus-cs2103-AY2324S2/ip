package duke;
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Assume Storage.load() returns List<String>
            // and TaskList has a constructor that accepts List<String> and converts it to List<Task>
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(); // Start with an empty TaskList if loading fails
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                // Create a Parser instance and parse the command
                Parser parser = new Parser(tasks, ui, storage);
                parser.parse(command);
                //storage.save(tasks.getTasks());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
