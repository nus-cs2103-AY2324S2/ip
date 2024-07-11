package parser;

import storage.Storage;
import task.Task;
import task.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    public void isMarkTaskTest() {
        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> temp = new ArrayList<>();
        temp = storage.getHistory();
        TaskList TodoList = new TaskList(temp);
        Storage archived = new Storage("./data/archived.txt");
        Parser parser = new Parser(TodoList, storage, archived);
        assertEquals(parser.isMarkTask("mark 1.2"), false);
    }

}
