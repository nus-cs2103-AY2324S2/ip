import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMO {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public BMO() {
        ui = new UI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadData(), ui, storage);
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        ui.printTutorial();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.receiveCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.printErrInvalidCommand();
            }
        }

        storage.saveData(tasks.toString());
        ui.salute();
    }

    public static void main(String[] args) {
        new BMO().run();
    }
}
