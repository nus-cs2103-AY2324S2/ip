package inputcommands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import snomexceptions.InvalidCommandDateFormatException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;

public class AddEventCommandTest {

    @Test
    public void addEventCommand_blankTaskDescription_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class, ()
                -> Command.makeCommand("event ").execute(lst));
        Assertions.assertEquals("Please do not leave your task description or date blank",
                t.getMessage());

    }

    @Test
    public void addEventCommand_blankTaskDescriptionWithSlash_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class, ()
                -> Command.makeCommand("event / ").execute(lst));
        Assertions.assertEquals("Please do not leave your task description or date blank",
                t.getMessage());

    }

    @Test
    public void addEventCommand_blankDate_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class, ()
                -> Command.makeCommand("event abc/ ").execute(lst));
        Assertions.assertEquals("Please do not leave your task description or date blank",
                t.getMessage());

    }


    @Test
    public void addEventCommand_blankStartDate_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class, ()
                -> Command.makeCommand("event abc/2002-01-01 ").execute(lst));
        Assertions.assertEquals("Please do not leave your task description or date blank",
                t.getMessage());

    }

    @Test
    public void addEventCommand_blankEndDate_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandTaskDescException.class, ()
                -> Command.makeCommand("event abc 2002-01-01/ ").execute(lst));
        Assertions.assertEquals("Please do not leave your task description or date blank",
                t.getMessage());

    }

    @Test
    public void addEventCommand_invalidStartDateFormat_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandDateFormatException.class, ()
                -> Command.makeCommand("event abc /invalid date format/2002-01-01 ").execute(lst));
        Assertions.assertEquals("Please ensure that your date(s) is of the format yyyy-mm-dd",
                t.getMessage());

    }

    @Test
    public void addEventCommand_invalidEndDateFormat_exceptionThrown() {
        TaskList lst = TaskList.makeTaskList();
        Throwable t = Assertions.assertThrows(InvalidCommandDateFormatException.class, ()
                -> Command.makeCommand("event abc /2002-01-01/invalid date format ").execute(lst));
        Assertions.assertEquals("Please ensure that your date(s) is of the format yyyy-mm-dd",
                t.getMessage());

    }


}
