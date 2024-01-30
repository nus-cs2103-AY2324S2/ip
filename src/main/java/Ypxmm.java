import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class Ypxmm {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ypxmm(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public void run() {
        ui.sayHello();
        ui.showLine();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                ArrayList<String> parsed = Parser.parse(input);
                Command command = Command.valueOf(parsed.get(0).toUpperCase());
                command.execute(tasks, ui, storage, parsed);
                ui.showLine();
                if (command.equals(Command.BYE)) {
                    isRunning = false;
                }
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
                ui.showLine();
            }
        }
        ui.sayGoodbye();
    }

    public static void main(String[] args) throws YpxmmException {

        new Ypxmm("/data/Ypxmm.txt").run();
    }
}
