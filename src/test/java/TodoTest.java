import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;
import task.ToDo;

public class TodoTest {

    @Test
    public void todoConstructTest() {
        Task task = new ToDo("Homework Test");
        assertEquals(task.toString(), "[T][ ] Homework Test");
    }

    @Test
    public void taskMarkTest() {
        Task task = new ToDo("Homework Test 2");
        task.mark();
        assertEquals(task.toString(), "[T][X] Homework Test 2");
    }

    @Test
    public void taskUnmarkTest() {
        Task task = new ToDo("Homework Test 3");
        task.mark();
        task.unmark();
        assertEquals(task.toString(), "[T][ ] Homework Test 3");
    }

    @Test
    public void taskFormatPrintTest() {
        Task task = new ToDo("Homework Test 4");
        task.mark();
        task.unmark();
        assertEquals(task.formatDataLine(), "ToDo|" + task.getCompleted() + "|Homework Test 4");
    }
}
