import java.util.ArrayList;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;

/**
 * The main class for duke chat bot.
 */

public class Duke {

    public static void main(String[] args) {
        Ui duke = new Ui("Zizhen");
        Storage storage = new Storage("./data/duke.txt");
        duke.greeting();

        ArrayList<Task> temp = new ArrayList<>();
        temp = storage.getHistory();
        TaskList todoList = new TaskList(temp);

        Parser parser = new Parser(todoList, storage);
        parser.parse();

        duke.exit();
    }
}
