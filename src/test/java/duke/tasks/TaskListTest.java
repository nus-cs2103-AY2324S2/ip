package duke.tasks;

import duke.tasks.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void dummyTest() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getTasksList(), new ArrayList<>());
    }

}
