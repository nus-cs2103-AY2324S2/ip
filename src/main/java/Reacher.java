import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reacher {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Reacher(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadList());
    }
    public static void main(String[] args) {
        new Reacher("./storage.txt").run();
    }
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readString();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ReacherException e) {
                ui.print(e.getMessage());
            }
        }
    }
}
