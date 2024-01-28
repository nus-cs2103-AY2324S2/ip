import commands.Command;
import exception.UncleBobException;
import task.*;
import ui.Ui;
import storage.Storage;
import parser.Parser;

import java.io.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void run() {
        ui.showGreetingMessage();
        boolean isExiting = false;
        while (!isExiting) {
            try {
                String inputs = ui.getUserCommand();
                Command c = Parser.parse(inputs);
                c.execute(tasks, storage, ui);
                isExiting = Command.isExit(c);
            } catch (UncleBobException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}