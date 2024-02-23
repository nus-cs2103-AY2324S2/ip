package podz.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import podz.exceptions.PodzException;
import podz.stubs.TaskListStub;
import podz.task.Task;

public class MarkCommandTest {
    
    @Test
    public void execute_validIndex_success() {
        TaskListStub taskListStub = new TaskListStub(new ArrayList<Task>());

        // taskList will have 3 tasks, so valid index from 0-2
        taskListStub.initDataThreeTasks();
        int indexMarked = 1;
        Task taskMarked = taskListStub.getTask(indexMarked);

        MarkCommand cmd = new MarkCommand(indexMarked);
        cmd.setTasks(taskListStub);
        try {
            cmd.execute();
        } catch (PodzException e) {
            fail();
        }

        // check if task marked
        assertEquals("X", taskMarked.getStatusIcon());
    }

    @Test
    public void execute_indexOutOfRange_exceptionThrown() {
        TaskListStub taskListStub = new TaskListStub(new ArrayList<Task>());
        taskListStub.initDataThreeTasks();

        // index < 0
        MarkCommand cmd = new MarkCommand(-1);
        cmd.setTasks(taskListStub);

        Exception tc1 = assertThrows(
            PodzException.class, 
            () -> cmd.execute()
        );
        
        assertEquals("Oh no!!! Invalid task index!", tc1.getMessage());

        // index > size
        Command cmd2 = new MarkCommand(taskListStub.getSize());
        cmd2.setTasks(taskListStub);
        Exception tc2 = assertThrows(
            PodzException.class, 
            () -> cmd2.execute()
        );
        
        assertEquals("Oh no!!! Invalid task index!", tc2.getMessage());
    }
}
