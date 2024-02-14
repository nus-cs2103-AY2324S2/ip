package duke;

import duke.tasks.Task;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("T,,F", t.toStorageString());
    }

    @Test
    public void toStorageString_markedToDo_success() {
        Task t = new ToDo("this");
        t.mark();
        assertEquals("T,this,T", t.toStorageString());
    }

    @Test
    public void toStorageString_unmarkedToDo_success() {
        Task t = new ToDo("this");
        t.mark();
        assertEquals("T,this,T", t.toStorageString());
        t.unmark();
        assertEquals("T,this,F", t.toStorageString());
    }

    @Test
    public void toStorageString_noNameToDo_success() {
        Task t = new ToDo("");
        assertEquals("T,,F", t.toStorageString());
    }

    // by right this should fail as it will mess up parsing
    // should fail when fixed in the future
    @Test
    public void toStorageString_commaInName_success() {
        Task t = new ToDo("mwa, ha, ha");
        assertEquals("T,mwa, ha, ha,F", t.toStorageString());
    }

}
