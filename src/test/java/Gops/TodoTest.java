package Gops;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void TodoTest() {
        String todoDescription = "Sleep";
        Todo todo = new Todo(todoDescription);
        String actualOutput = todo.stringPrinter();
        String expectedOutput = "T | 0 | Sleep";
        assertEquals(actualOutput, expectedOutput);
    }
}
