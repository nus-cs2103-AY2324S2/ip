package inputcommands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandIndexException;
import snomtask.Todo;
import snomtasklist.TaskList;






public class MarkTaskCommandTest {


    @Test
    public void markTaskCommand_blankIndex_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, () -> Command.makeCommand("mark ").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    public void markTaskCommand_invalidIndexRange_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                -> Command.makeCommand("mark 2").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    public void markTaskCommand_invalidIndex_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                -> Command.makeCommand("mark asdfasfasfd").execute(lst));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }


    @Test
    public void markTaskCommand_validIndex_correctStringReturned() {
        TaskList lst = TaskList.makeTaskList();
        lst.addTask(new Todo("read book1"));
        lst.addTask(new Todo("read book2"));
        try {
            Assertions.assertEquals(Integer.toString(2), Command.makeCommand("mark 2").execute(lst));
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

    }



}
