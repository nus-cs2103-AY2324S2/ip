package lilybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    public void checkAddTodo() {
        Task todo = new ToDo("review slides");
        String expectedString = "[T][ ] review slides";
        assertEquals(expectedString, todo.toString());
    }

    @Test
    public void checkAddDeadline() {
        Task ddl = new Deadline("review slides", "2024-04-04");
        String expectedString = "[D][ ] review slides (by Apr 04 2024)";
        assertEquals(expectedString, ddl.toString());
    }

    @Test
    public void checkAddEvent() {
        Task event = new Event("review slides", "2pm", "3pm Tue");
        String expectedString = "[E][ ] review slides (from 2pm to 3pm Tue)";
        assertEquals(expectedString, event.toString());
    }

}
