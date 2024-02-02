public class BartenderBob {
    private static final String NAME = "BartenderBob";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public BartenderBob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
//            System.out.println("Welcome back! I'm " + NAME + "\nHow's it going out there?");
            ui.showWelcomeMessage(NAME);
        } catch (BartenderBobException e) {
            ui.showLoadingError();
//            tasks = new TaskList();
        }
    }

//    public void leave() {
//        System.out.println("Bye! Another round next time!");
//    }
    public void run() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.handleInput(tasks, ui);
    }
    public static void main(String[] args) {
        new BartenderBob("./data/tasks.txt").run();
    }
}
