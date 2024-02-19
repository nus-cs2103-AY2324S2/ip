import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;

public class Damon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Damon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList(null);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println("Something wrong happens.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Damon("..\\Damon.txt").run();
    }
}

