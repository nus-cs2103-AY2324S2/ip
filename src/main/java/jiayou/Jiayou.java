package jiayou;

import jiayou.task.*;

public class Jiayou {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jiayou(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui(this.tasks);
        this.storage = new Storage(filePath, this.tasks);
        this.tasks.linkStorage(this.storage);
        this.storage.load();
    }

    public void run() {
        this.ui.greet();
    }

    public static void main(String[] args) {
        new Jiayou("./data/tasks.txt").run();
    }
}
