package duke;

import tasks.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    public Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
    }

    public void run() {
        ui.greet();
        tasks = storage.load();
        ui.chat(tasks, storage);
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("/data/", "duke.txt").run();
    }
}
