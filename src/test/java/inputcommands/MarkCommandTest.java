package inputcommands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomtask.Todo;
import snomtasklist.TaskList;






public class MarkCommandTest {


    @Test
    public void markCommandTest1() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, () -> Command.makeCommand("mark ").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    public void markCommandTest2() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                -> Command.makeCommand("mark 2").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    public void markCommandTest3() {
        TaskList lst = TaskList.makeTaskList();
        lst.addTask(new Todo("read book"));
        lst.addTask(new Todo("read book"));
        try {
            Assertions.assertEquals(Integer.toString(2), Command.makeCommand("mark 2").execute(lst));
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

    }

}
