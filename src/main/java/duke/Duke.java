package duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws RiriException, IOException {
        ui.introduction("riri");
        ui.chat(tasks);
        storage.writeToFile(tasks.toString());
        ui.exit();
    }

    public static void main(String[] args) throws IOException {
        try {
            new Duke("data/tasks.txt").run();
        } catch (RiriException e) {
            System.out.println(e.getMessage());
        }
    }
}
