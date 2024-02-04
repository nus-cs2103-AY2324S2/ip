package InputCommands;

import SnomExceptions.InvalidCommandDateFormatException;
import SnomExceptions.InvalidCommandTaskDescException;
import SnomTaskList.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddDeadlineCommandTest {

    @Test
    public void AddDeadlineCommandTest1() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class,
                () -> Command.makeCommand("deadline ").execute(lst));
        Assertions.assertEquals("Please dont leave your task description or date blank",
                t.getMessage());

    }

    @Test
    public void AddDeadlineCommandTest2() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class,
                () -> Command.makeCommand("deadline / ").execute(lst));
        Assertions.assertEquals("Please dont leave your task description or date blank",
                t.getMessage());

    }

    @Test
    public void AddDeadlineCommandTest3() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class,
                () -> Command.makeCommand("deadline abc/ ").execute(lst));
        Assertions.assertEquals("Please dont leave your task description or date blank",
                t.getMessage());

    }


    @Test
    public void AddDeadlineCommandTest4() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandDateFormatException.class,
                () -> Command.makeCommand("deadline abc/efg ").execute(lst));
        Assertions.assertEquals("Please ensure that your date(s) is of the format yyyy-mm-dd",
                t.getMessage());

    }
}
