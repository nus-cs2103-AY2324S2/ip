public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.load(taskList);
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
        }

    }

    public static void main(String[] args) {
        new Duke("./data/storedTasks.txt").run();
    }
}
