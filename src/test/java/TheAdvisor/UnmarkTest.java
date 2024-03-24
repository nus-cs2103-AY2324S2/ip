package theadvisor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class UnmarkTest {
    @Test
    public void test1() throws TheAdvisorException {
        String input = "sleep";
        ToDos toDos = new ToDos(input);

        assertFalse(toDos.isDone);

        String input2 = "return my book";
        ToDos toDos2 = new ToDos(input2);
        toDos2.markDone();
        assertTrue(toDos2.isDone);
    }

    @Test
    public void test2() throws TheAdvisorException {
        String input = "sleep";
        ToDos toDos = new ToDos(input);

        assertThrows(AssertionError.class, () -> {
            TaskList taskList = new TaskList();
            taskList.addToList(toDos);
            taskList.unmarkTask(0);
        });
    }
}
