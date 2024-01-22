import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import task.Task;
import task.taskList;

public class Parsing {
    public static taskList inputParsing(taskList tasklist) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            Command command = new Command(line, tasklist);
            command.process();
        }

        return tasklist;
    }
}
