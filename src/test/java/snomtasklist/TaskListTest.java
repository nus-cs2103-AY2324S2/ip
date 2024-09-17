package snomtasklist;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import snomexceptions.InvalidCommandIndexException;
import snomexceptions.InvalidCommandTaskDoneException;
import snomexceptions.InvalidCommandTaskNotDoneException;
import snomtask.Task;
import snomtask.Todo;

public class TaskListTest {

    @Test
    public void getTaskAtIndex_validIndex_correctStringreturned() {
        TaskList testTaskList = TaskList.makeTaskList();
        Task todoTest = new Todo("Read book");
        testTaskList.addTask(todoTest);
        try {
            Assertions.assertEquals(testTaskList.getTaskAtIndex(1).toString(), todoTest.toString());
        } catch (InvalidCommandIndexException e) {
            assert false : "This index is supposed to be valid";
        }
    }

    @Test
    public void getTaskAtIndex_invalidIndex_exceptionThrown() {
        TaskList testTaskList = TaskList.makeTaskList();
        Throwable t = assertThrows(InvalidCommandIndexException.class, ()
                 -> testTaskList.getTaskAtIndex(1));
        Assertions.assertEquals("Please ensure that the index you entered is valid", t.getMessage());
    }

    @Test
    void markTaskAtIndex_alreadyDoneTask_exceptionThrown() {
        TaskList testTaskList = TaskList.makeTaskList();
        Task todoTest = new Todo("Read book");
        try {
            todoTest.doTask();
        } catch (InvalidCommandTaskDoneException e) {
            assert false : "This task should not be done";
        }
        testTaskList.addTask(todoTest);
        Throwable t = assertThrows(InvalidCommandTaskDoneException.class, ()
                 -> testTaskList.markTaskAtIndex(1));
        Assertions.assertEquals("A task that is already done cannot be marked as done", t.getMessage());
    }

    @Test
    void unmarkTaskAtIndex_notDoneTask_exceptionThrown() {
        TaskList testTaskList = TaskList.makeTaskList();
        Task todoTest = new Todo("Read book");
        testTaskList.addTask(todoTest);
        Throwable t = assertThrows(InvalidCommandTaskNotDoneException.class, ()
                 -> testTaskList.unmarkTaskAtIndex(1));
        Assertions.assertEquals("A task that is not done cannot be marked as undone", t.getMessage());
    }

}
