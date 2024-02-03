package duke.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.tasks.ToDo;


public class TodoTest {
    @Test
    public void ToDo_toString(){
        assertEquals("[T][ ] homework", new ToDo("homework").toString());
    }
}
