package dibo;

import dibo.command.Command;
import dibo.exception.DiboException;

import java.util.ArrayList;

public class Dibo {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        Dibo dibo = new Dibo("./data/dibo.txt");
        dibo.run();
    }
    private Dibo(String path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(this.storage.loadData());
        } catch (DiboException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        this.ui.sayHi();
        boolean isBye = false;
        while (!isBye) {
            try {
                String fullCommand = ui.takeCommand();
                Command command = Parser.parse(fullCommand);
                command.run(tasks, ui, storage);
                isBye = command.isBye();
            } catch (DiboException e) {
                ui.showError(e.getMessage());
            }
        }
    }

}
