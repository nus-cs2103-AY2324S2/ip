package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void announcement_success() {
        assertEquals("New ToDo created!",new ToDo("Cook", "T").getAnnouncement());
    }

    @Test
    public void toString_success() {
        assertEquals("[T][] Cook", new ToDo("Cook", "T").toString());
    }

    @Test
    public void toString_mark_success() {
        ToDo Cook = new ToDo("Cook", "T");
        Cook.mark();
        assertEquals("[T][X] Cook",Cook.toString());
    }

    @Test
    public void saveString_success() {
        assertEquals("T|0|Cook", new ToDo("Cook", "T").saveString());
    }
}
