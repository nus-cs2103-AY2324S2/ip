package Parser;

import Storage.Storage;
import Task.Task;
import Task.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void isMarkTaskTest() {
        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> temp = new ArrayList<>();
        temp = storage.getHistory();
        TaskList TodoList = new TaskList(temp);
        Parser parser = new Parser(TodoList, storage);
        assertEquals(parser.isMarkTask("mark 1.2"), false);
    }
}
