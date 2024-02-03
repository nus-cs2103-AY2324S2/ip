package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    
    
    @Test
    public void describe_normalToDo_success() {
        assertEquals(new duke.ToDo("this").describe(), "[T][ ] this");
    }
     
    @Test
    public void describe_markedToDo_success() {
        Task t = new duke.ToDo("this");
        t.mark();
        assertEquals(t.describe(), "[T][X] this");
    }
    
    @Test
    public void describe_unmarkedToDo_success() {
        Task t = new duke.ToDo("this");
        t.mark();
        assertEquals(t.describe(), "[T][X] this");
        t.unmark();
        assertEquals(t.describe(), "[T][ ] this");
    }

    @Test
    public void describe_noNameToDo_success() {
        Task t = new duke.ToDo("");
        assertEquals(t.describe(), "[T][ ] ");
    }
    
    @Test
    public void toStorageString_normalToDo_success() {
        assertEquals(new duke.ToDo("this").toStorageString(), "T,this,F");
    }
     
    @Test
    public void toStorageString_markedToDo_success() {
        Task t = new duke.ToDo("this");
        t.mark();
        assertEquals(t.toStorageString(), "T,this,T");
    }
    
    @Test
    public void toStorageString_unmarkedToDo_success() {
        Task t = new duke.ToDo("this");
        t.mark();
        assertEquals(t.toStorageString(), "T,this,T");
        t.unmark();
        assertEquals(t.toStorageString(), "T,this,F");
    }

    @Test
    public void toStorageString_noNameToDo_success() {
        Task t = new duke.ToDo("");
        assertEquals(t.toStorageString(), "T,,F");
    }
    
    // by right this should fail as it will mess up parsing
    // will fail when fixed in the future
    @Test
    public void toStorageString_commaInName_success() {
        Task t = new duke.ToDo("mwa, ha, ha");
        assertEquals(t.toStorageString(), "T,mwa, ha, ha,F");
    }

}