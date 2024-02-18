
import duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class TodosTest {
    @Test
    public void checkToStringTest(){
        ToDo t = new ToDo("Read book");
        assertEquals(t.toString(), "[T][ ] Read book");
    }

    @Test
    public void checkToDosMark() {
        ToDo t = new ToDo("Read Book");
        t.markAsDone();
        assertEquals(t.toString(), "[T][X] Read Book");
        t.markAsUndone();
        assertEquals(t.toString(), "[T][ ] Read Book");
    }
}
