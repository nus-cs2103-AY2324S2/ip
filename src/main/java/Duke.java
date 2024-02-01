import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws TaskNotFoundException, UnknownCommandException,
            InvalidSyntaxException, IOException, InvalidDateTimeException {
        Ui ui = new Ui();
        Parser parser = new Parser();
        String fileName = "./data/duke.txt";
        Storage storage = new Storage(fileName);
        List taskList = new List(storage.loadTasks());
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();
            isExit = parser.parse(input, taskList, ui, storage);
        }
    }

}
