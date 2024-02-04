package InputCommands;

import SnomExceptions.InvalidCommandException;
import SnomExceptions.InvalidCommandIndexException;
import SnomTask.Todo;
import SnomTaskList.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {


    @Test
    public void MarkCommandTest() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, () -> Command.makeCommand("mark ").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid",t.getMessage());
    }

    @Test
    public void MarkCommandTest2() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, () -> Command.makeCommand("mark 2").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid",t.getMessage());
    }

    @Test
    public void MarkCommandTest3() {
        TaskList lst = TaskList.makeTaskList();
        lst.AddTask(new Todo("read book"));
        lst.AddTask(new Todo("read book"));
        try {
            Assertions.assertEquals(Integer.toString(2), Command.makeCommand("mark 2").execute(lst));
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

    }

}
