import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws TaskNotFoundException, UnknownCommandException,
            InvalidSyntaxException, IOException, InvalidDateTimeException {
        Ui ui = new Ui();
        Parser parser = new Parser();
        List taskList = new List(new ArrayList<>());
        String fileName = "./data/duke.txt";
        File f = new File(fileName);
        if (!f.exists()) {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        } else {
            taskList.loadTasks();
        }
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();
            isExit = parser.parse(input, taskList, ui);
        }
    }

}
