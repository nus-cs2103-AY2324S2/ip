package alfred.test;
import alfred.task.Deadline;
import alfred.task.Todo;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class TaskTest {
    @Test
    void Todo_creationTest_success() {
        Todo todo = new Todo("Read book");
        assertEquals("Read book", todo.getName());
    }

    @Test
    void Todo_markDone_success() {
        Todo todo = new Todo("Read book");
        todo.makeDone();
        assertTrue(todo.isDone());
    }

    @Test
    void Deadline_creationTest_success() {
        Deadline deadline = new Deadline("Read book", "31/12/2021 1800");
        assertEquals("Read book", deadline.getName());
        LocalDateTime test = LocalDateTime.parse("31/12/2021 1800",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        assertEquals(test.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a")), deadline.getRawTime());
        deadline.makeDone();
        assertTrue(deadline.isDone());
    }

    @Test
    void Deadline_markDone_success() {
        Deadline deadline = new Deadline("Read book", "tomorrow");
        deadline.makeDone();
        assertTrue(deadline.isDone());
    }



}
