package thecount;

import thecount.storage.Storage;
import thecount.task.TaskList;
import thecount.ui.Ui;

public class TheCount {

    private Storage loader;
    private TaskList tasks;
    private Ui ui;

    public TheCount() {
        this.tasks = new TaskList();
        this.loader = new Storage(this.tasks);
        this.ui = new Ui(this.tasks, this.loader);
    }

    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        new TheCount().run();
    }
}