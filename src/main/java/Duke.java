public class Duke {
    public static final String LOGO = "\n"
            + "  _____                _                   _\n"
            + " |  __ \\              | |                 | |\n"
            + " | |__) | __ _  _ __  | |__    __ _   ___ | |\n"
            + " |  _  / / _` || '_ \\ | '_ \\  / _` | / _ \\| |\n"
            + " | | \\ \\| (_| || |_) || | | || (_| ||  __/| |\n"
            + " |_|  \\_\\\\__,_|| .__/ |_| |_| \\__,_| \\___||_|\n"
            + "               | |\n"
            + "               |_|\n";
    public static final String BOT_NAME = "Raphael";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    private void run() {
        this.ui.showWelcome();
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
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
