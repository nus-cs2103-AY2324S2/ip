package shuheng;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import shuheng.tasks.PriorityLevel;
import shuheng.tasks.ToDo;

public class TaskListTest {
    @Test
    public void testInsert() {
        TaskList taskList = new TaskList();
        ToDo toAdd = new ToDo("do stuff", PriorityLevel.LOW);
        ToDo toAdd2 = new ToDo("do more stuff", PriorityLevel.HIGH);
        taskList.addTaskTest(toAdd);
        assertEquals(1, taskList.getLength());
        taskList.addTaskTest(toAdd2);
        assertEquals(2, taskList.getLength());
    }
}
