import java.io.IOException;

public class Awex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Awex(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            ui.greeting();
            Parser.parse(this.tasks, this.ui);
            storage.store(tasks);
            ui.farewell();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Awex("./list.txt").run();
    }
}