package inputcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;


public class AddTodoCommandTest {

    @Test
    public void addTodoCommandTest1() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class, ()
                 -> Command.makeCommand("todo ").execute(lst));
        Assertions.assertEquals("Please dont leave your task description or date blank",
                t.getMessage());

    }

}
