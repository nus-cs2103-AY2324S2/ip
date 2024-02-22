package homie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;



public class TodoTest {
    @Test
    public void no_command_exceptionThrown() {
        String line = "____________________________________________________________";
        try {
            Todo todo = new Todo("");
            assertEquals(todo, new Todo(""));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("\n" + line + "\nOpps!!! The description of a todo cannot be empty.\n" + line, e.getMessage());
        }
    }
}
