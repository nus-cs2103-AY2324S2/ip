package bartenderbob;
public class BartenderBob {
    private static final String NAME = "bartenderbob";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public BartenderBob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showWelcomeMessage(NAME);
        } catch (BartenderBobException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.handleInput(tasks, ui);
    }
    public static void main(String[] args) {
        new BartenderBob("./data/tasks.txt").run();
    }
}
