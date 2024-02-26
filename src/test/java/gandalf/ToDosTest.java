package gandalf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gandalf.tasktypes.ToDos;

public class ToDosTest {

    private ToDos testTask = new ToDos("read a book");
    @Test
    public void toStringTest() {
        assertEquals(testTask.toString(), "[T][ ] read a book");
    }

}
