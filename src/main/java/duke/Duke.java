package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.function.ToDoubleFunction;

class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.open();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseToCommand(fullCommand);
                c.execute(tasks, ui);
                ui.showLine();
                isExit = c.isExit();
            } catch (DukeException ex) {
                System.out.println(ex.toString());
                ui.showLine();
            }
        }
        ui.close();
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
