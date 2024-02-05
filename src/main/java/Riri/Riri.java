package Riri;

import java.io.IOException;

public class Riri {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Riri(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws RiriException, IOException {
        ui.introduction("Riri");
        ui.chat(tasks);
        storage.writeToFile(tasks.toString());
        ui.exit();
    }

    public static void main(String[] args) throws RiriException, IOException {
        new Riri("data/tasks.txt").run();
    }
}
