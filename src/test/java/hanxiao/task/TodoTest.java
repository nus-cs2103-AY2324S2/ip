package hanxiao.task;

import hanxiao.exception.WrongUsageException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    void test_getTaskTypeIcon() {
        Todo todo = new Todo("Trivial description");

        assertEquals("T",todo.getTaskTypeIcon());
    }

    @Test
    void test_getTaskType() {
        Todo todo = new Todo("Trivial description");

        assertEquals("todo",todo.getTaskType());
    }

    @Test
    void test_isTimeForStart() {
        Todo todo = new Todo("Trivial description");
        todo.isDone = true;

        assertEquals(false,todo.isTimeForStart(LocalDate.now()));
    }

    @Test
    void test_equals() {
        Todo todo = new Todo("Trivial description");
        Todo toodoo = new Todo("Trivial description");
        Todo toodo = new Todo("Trivial DDdescription");

        assertEquals(true,todo.equals(toodoo));
        assertEquals(false,todo.equals(toodo));
    }

    @Test
    void test_compareTo() {
        Todo todo = new Todo("Trivial description");
        Todo toodo = new Todo("AAATrivial DDdescription");

        assertEquals(false,todo.compareTo(toodo)<0);
        assertEquals(true,toodo.compareTo(todo)<0);
    }

    @Test
    void test_updateTask_throwWrongUsageException() throws WrongUsageException {
        Todo todo = new Todo("Trivial description");
        Todo toodo = new Todo("AAATrivial DDdescription");
        todo.updateTask("/des", "AAATrivial DDdescription");
        assertEquals(true,toodo.compareTo(todo)==0);
    }
}
