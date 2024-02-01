package jade.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void getStatusIcon() throws Exception {
        // task with done status will return "X"
        assertEquals("X", new Task("a", true).getStatusIcon());

        // task with undone status will return " "
        assertEquals(" ",  new Task("a").getStatusIcon());
    }
}