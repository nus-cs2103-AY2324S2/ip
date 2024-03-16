package mainfiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    public void sizeListTest() {
        assertEquals(0, new TaskList(new Storage()).size());
    }
}

