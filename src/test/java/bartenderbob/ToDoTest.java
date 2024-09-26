package bartenderbob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDo_withoutIsDoneParameter_blankStatus() {
        ToDo test = new ToDo("Testing!");
        assertNotNull(test, "ToDo instance should not be null");
        assertEquals(test.show(), "[T][ ] Testing!");
    }
    @Test
    public void toDo_trueIsDoneParameter_blankStatus() {
        ToDo test = new ToDo("Testing!", true);
        assertNotNull(test, "ToDo instance should not be null");
        assertEquals(test.show(), "[T][X] Testing!");
    }
    @Test
    public void toDo_falseIsDoneParameter_blankStatus() {
        ToDo test = new ToDo("Testing!", false);
        assertNotNull(test, "ToDo instance should not be null");
        assertEquals(test.show(), "[T][ ] Testing!");
    }

}
