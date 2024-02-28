import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;
import task.ToDo;

public class TodoTest {

    @Test
    public void todoConstructor_createToDo_success() {
        Task task = new ToDo("Homework Test");
        assertEquals(task.toString(), "[T][ ] Homework Test");
    }

    @Test
    public void todoConstructor_createToDoAndMark_success() {
        Task task = new ToDo("Homework Test 2");
        task.mark();
        assertEquals(task.toString(), "[T][X] Homework Test 2");
    }

    @Test
    public void todoConstructor_createToDoAndMarkAndUnmark_success() {
        Task task = new ToDo("Homework Test 3");
        task.mark();
        task.unmark();
        assertEquals(task.toString(), "[T][ ] Homework Test 3");
    }

    @Test
    public void todoConstructor_checkFormatForSavingFile_success() {
        Task task = new ToDo("Homework Test 4");
        task.mark();
        task.unmark();
        assertEquals(task.formatDataLine(), "ToDo|" + task.getCompleted() + "|Homework Test 4");
    }
}
