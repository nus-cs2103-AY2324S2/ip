import static org.junit.jupiter.api.Assertions.assertEquals;

import luke.List;
import luke.Storage;
import luke.Ui;
import luke.exception.InvalidSyntaxException;
import luke.exception.SaveFileCorruptedException;
import luke.exception.UnknownCommandException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.ToDo;
import org.junit.jupiter.api.Test;

import luke.Parser;

import java.io.IOException;
import java.util.ArrayList;

public class ParserTest {
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private String fileName = "./testStorage/data.txt";
    private Storage storage = new Storage(fileName);

    public ParserTest() throws IOException, SaveFileCorruptedException {
    }

    @Test
    public void testBye() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        assertEquals("Bye. Hope to see you again soon!\n",
                parser.parse("bye", taskList, ui, storage)
        );
    }

    @Test
    public void testBye2() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        assertEquals(new InvalidSyntaxException("bye").toString(), parser.parse("bye ", taskList, ui, storage));
    }

    @Test
    public void testBye3() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        assertEquals(new UnknownCommandException().toString(), parser.parse(" bye", taskList, ui, storage));
    }

    @Test
    public void testList() throws IOException {
        List taskList = new List(new ArrayList<>());
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new Deadline("homework", parser.toLocalDateTime("2024-02-02 23:59")), storage, ui);
        taskList.addTask(new Event("study", parser.toLocalDateTime("2024-02-02 08:00"),
                parser.toLocalDateTime("2024-02-02 12:00")), storage, ui);
        storage.clearData();
        assertEquals("Here are the tasks in your list:\n"
                + taskList.toString() + "\n",
                parser.parse("list", taskList, ui, storage)
        );
    }

    @Test
    public void testList2() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        assertEquals(new InvalidSyntaxException("list").toString(), parser.parse("list ", taskList, ui, storage));
    }
}
