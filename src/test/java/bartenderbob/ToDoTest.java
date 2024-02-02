package bartenderbob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ToDoTest {
    @Test
    public void ToDo_withoutIsDoneParameter_blankStatus(){
        ToDo test = new ToDo("Testing!");
        assertNotNull(test, "ToDo instance should not be null");
        assertEquals(test.show(), "[T][ ] Testing!");
    }
    @Test
    public void ToDo_trueIsDoneParameter_blankStatus(){
        ToDo test = new ToDo("Testing!", true);
        assertNotNull(test, "ToDo instance should not be null");
        assertEquals(test.show(), "[T][X] Testing!");
    }
    @Test
    public void ToDo_falseIsDoneParameter_blankStatus(){
        ToDo test = new ToDo("Testing!", false);
        assertNotNull(test, "ToDo instance should not be null");
        assertEquals(test.show(), "[T][ ] Testing!");
    }

}
