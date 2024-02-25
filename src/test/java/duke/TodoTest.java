package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Priority;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TodoTest {
    @Test
    public void describe_normalToDo_success() {
        Task t = new ToDo("this");
        assertEquals("[T][ ] this", t.describe());
    }

    @Test
    public void describe_markedToDo_success() {
        Task t = new ToDo("this");
        t.mark();
        assertEquals("[T][X] this", t.describe());
    }

    @Test
    public void describe_prioritisedToDo_success() {
        Task t = new ToDo("this");
        t.setPriority(Priority.HIGH);
        assertEquals("[T][ ] \u2605 this", t.describe());
    }

    @Test
    public void describe_unmarkedToDo_success() {
        Task t = new ToDo("this");
        t.mark();
        assertEquals("[T][X] this", t.describe());
        t.unmark();
        assertEquals("[T][ ] this", t.describe());
    }

    @Test
    public void describe_noNameToDo_success() {
        Task t = new ToDo("");
        assertEquals("[T][ ] ", t.describe());
    }

    @Test
    public void toStorageString_normalToDo_success() {
        Task t = new ToDo("");
        assertEquals("T,,F,L", t.toStorageString());
    }

    @Test
    public void toStorageString_markedToDo_success() {
        Task t = new ToDo("this");
        t.mark();
        assertEquals("T,this,T,L", t.toStorageString());
    }

    @Test
    public void toStorageString_prioritisedToDo_success() {
        Task t = new ToDo("this");
        t.setPriority(Priority.HIGH);
        assertEquals("T,this,F,H", t.toStorageString());
    }

    @Test
    public void toStorageString_unmarkedToDo_success() {
        Task t = new ToDo("this");
        t.mark();
        assertEquals("T,this,T,L", t.toStorageString());
        t.unmark();
        assertEquals("T,this,F,L", t.toStorageString());
    }

    @Test
    public void toStorageString_noNameToDo_success() {
        Task t = new ToDo("");
        assertEquals("T,,F,L", t.toStorageString());
    }

    // by right this should fail as it will mess up parsing
    // should fail when fixed in the future
    @Test
    public void toStorageString_commaInName_success() {
        Task t = new ToDo("mwa, ha, ha");
        assertEquals("T,mwa, ha, ha,F,L", t.toStorageString());
    }

}
