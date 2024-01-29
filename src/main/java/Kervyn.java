import Tasks.TaskList;

import java.io.IOException;
public class Kervyn {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Kervyn(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readTasks());
        } catch (IOException e) {
            taskList = new TaskList();
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        ui.startChatBot(this.taskList, this.storage);
    }
    public static void main(String[] args) throws IOException {
        new Kervyn("data/tasks.txt").run();
    }
}
