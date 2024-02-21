import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import luke.List;
import luke.Parser;
import luke.Storage;
import luke.Ui;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.ToDo;

/**
 * Turn off assertions to test.
 */
public class ListTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage("./testData/test.txt");
    private Parser parser = new Parser();

    public ListTest() throws IOException {
    }

    @Test
    public void testAddToDo() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new ToDo("eat"), storage, ui);
        assertEquals("1.[T][ ] eat", taskList.toString());
    }

    @Test
    public void testAddDeadline() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new Deadline("homework", parser.toLocalDateTime("2024-02-02 23:59")), storage, ui);
        assertEquals("1.[D][ ] homework (by: 02 Feb 2024 23:59)", taskList.toString());
    }

    @Test
    public void testAddEvent() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new Event("study", parser.toLocalDateTime("2024-02-02 08:00"),
                parser.toLocalDateTime("2024-02-02 12:00")), storage, ui);
        assertEquals("1.[E][ ] study (from: 02 Feb 2024 08:00 to: 02 Feb 2024 12:00)", taskList.toString());
    }

    @Test
    public void testMarkTask() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.markTask(1, storage, ui);
        String expected = "1.[T][ ] eat\n"
                + "2.[T][X] eat\n"
                + "3.[T][ ] eat";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testUnmarkTask() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.markTask(1, storage, ui);
        taskList.unmarkTask(1, storage, ui);
        String expected = "1.[T][ ] eat\n"
                + "2.[T][ ] eat\n"
                + "3.[T][ ] eat";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testDeleteTask() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new ToDo("sleep"), storage, ui);
        taskList.addTask(new ToDo("study"), storage, ui);
        taskList.deleteTask(1, storage, ui);
        String expected = "1.[T][ ] eat\n"
                + "2.[T][ ] study";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testToString() throws IOException {
        List taskList = new List(new ArrayList<>());
        storage.clearData();
        taskList.addTask(new ToDo("eat"), storage, ui);
        taskList.addTask(new Deadline("homework", parser.toLocalDateTime("2024-02-02 23:59")), storage, ui);
        taskList.markTask(0, storage, ui);
        taskList.addTask(new Event("study", parser.toLocalDateTime("2024-02-02 08:00"),
                parser.toLocalDateTime("2024-02-02 12:00")), storage, ui);
        String expected = "1.[T][X] eat\n"
                + "2.[D][ ] homework (by: 02 Feb 2024 23:59)\n"
                + "3.[E][ ] study (from: 02 Feb 2024 08:00 to: 02 Feb 2024 12:00)";
        assertEquals(expected, taskList.toString());
    }
}
