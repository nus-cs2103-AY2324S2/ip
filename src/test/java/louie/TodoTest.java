package louie;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import louie.tasks.Priority;
import louie.tasks.Task;
import louie.tasks.ToDo;

public class TodoTest {
    private static Task t;
    @Test
    public void describe_normalToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo("this"));
        assertEquals("[T][ ] this", t.describe());
    }

    @Test
    public void describe_markedToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo("this"));
        t.mark();
        assertEquals("[T][X] this", t.describe());
    }

    @Test
    public void describe_prioritisedToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo("this"));
        t.setPriority(Priority.HIGH);
        assertEquals("[T][ ] \u2605 this", t.describe());
    }

    @Test
    public void describe_unmarkedToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo("this"));
        t.mark();
        assertEquals("[T][X] this", t.describe());
        t.unmark();
        assertEquals("[T][ ] this", t.describe());
    }

    @Test
    public void describe_noNameToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo(""));
        assertEquals("[T][ ] ", t.describe());
    }

    @Test
    public void toStorageString_normalToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo(""));
        assertEquals("T,,F,L", t.toStorageString());
    }

    @Test
    public void toStorageString_markedToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo("this"));
        t.mark();
        assertEquals("T,this,T,L", t.toStorageString());
    }

    @Test
    public void toStorageString_prioritisedToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo("this"));
        t.setPriority(Priority.HIGH);
        assertEquals("T,this,F,H", t.toStorageString());
    }

    @Test
    public void toStorageString_unmarkedToDo_success() {

        assertDoesNotThrow(() -> t = new ToDo("this"));
        t.mark();
        assertEquals("T,this,T,L", t.toStorageString());
        t.unmark();
        assertEquals("T,this,F,L", t.toStorageString());
    }

    @Test
    public void toStorageString_noNameToDo_success() {
        assertDoesNotThrow(() -> t = new ToDo(""));
        assertEquals("T,,F,L", t.toStorageString());
    }
}
