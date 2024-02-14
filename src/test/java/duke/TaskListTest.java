package duke;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    TaskList t;

    @BeforeEach
    public void init() {
        t = new TaskList();
        t.add(new ToDo("name"));
        t.add(new ToDo("game"));
        t.add(new ToDo("nam"));
    }

    @AfterEach
    public void end() {
        t = null;
    }

    @Test
    public void filterSubString_requestWholeInput_success() {
        assertEquals("1.[T][ ] name\n"
                   + "2.[T][ ] game\n"
                   + "3.[T][ ] nam",
                t.filterSubString("am"));
    }

    @Test
    public void filterSubString_requestOneItem_success() {
        assertEquals("2.[T][ ] game\n",
                t.filterSubString("gam"));

    }

    @Test
    public void filterSubString_requestSomeItem_success() {
        assertEquals("1.[T][ ] name\n"
                   + "3.[T][ ] nam",
                t.filterSubString("nam"));
    }

    @Test
    public void filterSubString_requestNothing_success() {
        assertEquals("",
                t.filterSubString("wahoo"));
    }
}
