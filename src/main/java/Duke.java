import java.util.ArrayList;

import Task.Task;
import Task.TaskList;
import Ui.Ui;
import Storage.Storage;
import Parser.Parser;

public class Duke {

    public static void main(String[] args) {
        Ui duke = new Ui("Zizhen");
        Storage storage = new Storage("./data/duke.txt");
        duke.greeting();

        ArrayList<Task> temp = new ArrayList<>();
        temp = storage.getHistory();
        TaskList TodoList = new TaskList(temp);

        Parser parser = new Parser(TodoList, storage);
        parser.parse();

        duke.exit();
    }
}
