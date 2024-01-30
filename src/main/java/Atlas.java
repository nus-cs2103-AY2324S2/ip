import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Atlas {
    private final String DATA_PATH = "./data/Atlas.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Atlas() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(tasks, DATA_PATH);
    }

    public static void main(String[] args) {
        Atlas atlas = new Atlas();
        atlas.run();
    }

    public void run() {
        ui.showGreeting();
        storage.load();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command cmd = Parser.parse(input, tasks, ui, storage);
                cmd.execute();
            } catch (AtlasException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("Date format should be:YYYY-MM-DD HHmm");
            }
        }


    }

}
