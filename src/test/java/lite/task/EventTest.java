package lite.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void saveToFileTest() {
        Task task = new Event("something", "from Monday 4pm", "to Tuesday 2pm");
        assertEquals("E!0!something!from Monday 4pm!to Tuesday 2pm\n", task.saveToFile());
        task.setDone();
        assertEquals("E!1!something!from Monday 4pm!to Tuesday 2pm\n", task.saveToFile());
        task.setUndone();
        assertEquals("E!0!something!from Monday 4pm!to Tuesday 2pm\n", task.saveToFile());
    }

    @Test
    public void toStringTest() {
        Task task = new Event("something", "from Monday 4pm", "to Tuesday 2pm");
        assertEquals("[E][] something (from: Monday 4pm to: Tuesday 2pm)", task.toString());
        task.setDone();
        assertEquals("[E][X] something (from: Monday 4pm to: Tuesday 2pm)", task.toString());
    }
}
