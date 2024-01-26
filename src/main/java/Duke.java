import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RolandException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.boot();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.spacer();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RolandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.spacer();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("/Users/lay/IdeaProjects/ip/src/main/java/roland.txt").run();
    }



}