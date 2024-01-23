import controller.Command;
import controller.ExitCommand;
import controller.GreetCommand;
import duke.Parser;
import duke.Storage;
import model.TaskList;


import java.util.*;

public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        if (storage.isFileExists()) {
            this.taskList = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new TaskList();
            storage.update(taskList.getTaskList());
        }
    }

    public void launch() {
        new GreetCommand().execute(storage);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a command:\n");
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    new ExitCommand().execute(storage);
                    break;
                } else {
                    Command c = new Parser(input, taskList).parse();
                    c.execute(storage);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Duke("./data.dat").launch();
    }
}