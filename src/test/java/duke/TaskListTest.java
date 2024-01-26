package duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskStub extends Task {
    String identifier;

    public TaskStub(String identifier) {
        super("stub");
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return this.identifier;
    }
}

public class TaskListTest {
    private TaskList setupThreeTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TaskStub("A"));
        taskList.addTask(new TaskStub("B"));
        taskList.addTask(new TaskStub("C"));
        return taskList;
    }

    @Test
    public void addTask_normalInput_taskAdded() {
        TaskList taskList = this.setupThreeTasks();
        assertEquals(3, taskList.getSize());
        assertEquals("A", taskList.getTask(0).toString());
        assertEquals("B", taskList.getTask(1).toString());
        assertEquals("C", taskList.getTask(2).toString());
    }

    @Test
    public void deleteTask_normalInput_taskDeleted() {
        TaskList taskList = this.setupThreeTasks();
        taskList.deleteTask(1);
        assertEquals(2, taskList.getSize());
        assertEquals("A", taskList.getTask(0).toString());
        assertEquals("C", taskList.getTask(1).toString());
    }

    @Test
    public void listTasks_normalInput_tasksListed() {
        TaskList taskList = this.setupThreeTasks();
        ArrayList<String> tasks = taskList.listTasks();
        assertEquals(3, tasks.size());
        assertEquals("1.A", tasks.get(0));
        assertEquals("2.B", tasks.get(1));
        assertEquals("3.C", tasks.get(2));
    }

    @Test
    public void getTask_outOfRange_exceptionThrown() {
        TaskList taskList = this.setupThreeTasks();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(3));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(-1));
    }
}
