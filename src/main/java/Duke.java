import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.load(taskList);
        parser = new Parser(taskList);
    }

    public void run() {
        parser.parse();
        storage.store(taskList);
    }

    public static void main(String[] args) {
        new Duke("./data/storedTasks.txt").run();
    }
}
