
import duke.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class TodosTest {
    @Test
    public void checkToStringTest(){
        ToDos t = new ToDos("Read book");
        assertEquals(t.toString(), "[T][ ] Read book");
    }

    @Test
    public void checkToDosMark() {
        ToDos t = new ToDos("Read Book");
        t.markAsDone();
        assertEquals(t.toString(), "[T][X] Read Book");
        t.markAsUndone();
        assertEquals(t.toString(), "[T][ ] Read Book");
    }
}
