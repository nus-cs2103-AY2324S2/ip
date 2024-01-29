package bytebuddy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Buy groceries");

        String expectedOutput = "[T][✕] Buy groceries";
        String actualOutput = todo.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTodoTextFormattedOutput() {
        Todo todo = new Todo("Buy groceries");

        String expectedOutput = "T | 0 | Buy groceries";
        String actualOutput = todo.getTextFormattedOutput();

        assertEquals(expectedOutput, actualOutput);
    }

}
