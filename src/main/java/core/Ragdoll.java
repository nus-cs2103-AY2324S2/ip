package core;

import commands.Command;
import data.Storage;
import tasks.TaskList;
import java.nio.file.Paths;

public class Ragdoll{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ragdoll(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Ragdoll(Paths.get("data", "tasks.txt").toString()).run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while(!isExit) {
            String input = ui.readCommand();
            ui.showLine();

            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();

            ui.showLine();
        }
    }
}
