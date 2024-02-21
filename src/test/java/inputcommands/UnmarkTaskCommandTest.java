package inputcommands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomtask.Todo;
import snomtasklist.TaskList;






public class UnmarkTaskCommandTest {


    @Test
    public void unmarkTaskCommand_blankIndex_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                 -> Command.makeCommand("unmark ").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    public void unmarkTaskCommand_invalidIndexRange_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                -> Command.makeCommand("unmark 2").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    public void unmarkTaskCommand_invalidIndex_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                -> Command.makeCommand("unmark asdfasfasfd").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }


    @Test
    public void unmarkTaskCommand_validIndex_correctStringReturned() {
        TaskList lst = TaskList.makeTaskList();
        lst.addTask(new Todo("read book1"));
        lst.addTask(new Todo("read book2"));
        try {
            Assertions.assertEquals(Integer.toString(2), Command.makeCommand("unmark 2").execute(lst));
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

    }



}
