public class Luke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (LukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        ui.handleInput();
        storage.saveFile(tasks);
        ui.end();
    }

    public static void main(String[] args) {
        new Luke("data/tasks.txt").run();
    }

}
