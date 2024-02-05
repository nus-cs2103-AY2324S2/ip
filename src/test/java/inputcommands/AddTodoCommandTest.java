package inputcommands;

import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddTodoCommandTest {

    @Test
    public void AddTodoCommandTest1() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class,
                () -> Command.makeCommand("todo ").execute(lst));
        Assertions.assertEquals("Please dont leave your task description or date blank",
                t.getMessage());

    }

}
