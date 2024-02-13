public class Luke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Luke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
            ui = new Ui(taskList);
        } catch (LukeException e) {
            taskList = new TaskList();
            ui = new Ui();
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.welcome();
        ui.handleInput();
        storage.saveFile(taskList);
        ui.end();
    }

    public static void main(String[] args) {
        new Luke("data/tasks.txt").run();
    }

}
