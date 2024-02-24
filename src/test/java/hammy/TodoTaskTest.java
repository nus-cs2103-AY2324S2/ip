package hammy;
import hammy.storage.Storage;
import hammy.task.*;
import hammy.response.*;
import hammy.parser.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        // Create a sample task list and initialize necessary objects
        ui = new Ui("Hammy");
        taskList = new TaskList(new ArrayList<>(), ui);
        parser = new Parser(ui, new Storage("./data/tasklist.txt"), taskList);
    }

    @Test
    public void testTodoTaskToString1() {
        // Test the toString() method of TodoTask
        Task todoTask = new TodoTask("Buy groceries");
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todoTask.toString());
    }

    @Test
    public void testTodoTaskToString2() {
        // Test the toString() method of TodoTask
        Task todoTask = new TodoTask("Cook dinner");
        String expected = "[T][ ] Cook dinner";
        assertEquals(expected, todoTask.toString());
    }

}
