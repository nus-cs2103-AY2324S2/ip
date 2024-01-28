package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDos;

public class TaskListTest {
    @Test
    public void testInsert() {
        TaskList taskList = new TaskList();
        ToDos toAdd = new ToDos("do stuff");
        ToDos toAdd2 = new ToDos("do more stuff");
        taskList.addTaskTest(toAdd);
        assertEquals(1, taskList.getLength());
        taskList.addTaskTest(toAdd2);
        assertEquals(2, taskList.getLength());
    }
}
