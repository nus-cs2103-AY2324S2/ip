package lilybot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LilyBotTest {
    Task taskTodo = new ToDo("finish hk");
    Task taskDdl = new Deadline("read book", "2022-12-12");
    Task taskEvent = new Event("attend meeting", "8am", "10am");

    @Test
    public void testMarkDone() {
        taskTodo.mark();
        assertEquals(taskTodo.getStatus(), true);
    }

    @Test
    public void testMarkNotDone() {
        taskDdl.unmark();
        assertEquals(taskDdl.getStatus(), false);
    }

    @Test
    public void testEventDescription() {
        assertEquals(taskEvent.getDescription(),"attend meeting");
    }

}
