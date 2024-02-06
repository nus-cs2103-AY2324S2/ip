import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Liv {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Liv(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() {
        ui.displayGreetCommand();
        storage.loadDataFile();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.displayBar(); // show the divider line ("_______")
                Command c = Parser.parse(input);
                c.execute(tasks, ui);
                if (c.changedData()) {
                    storage.saveTaskToFile();
                }
                isExit = c.isExit();
            } catch (LivException e) {
                ui.displayErrorMessage(e.getMessage());
            } finally {
                ui.displayBar();
            }
        }
    }
    public static void main(String[] args) {
        new Liv("data/Liv.txt").run();
    }
}
