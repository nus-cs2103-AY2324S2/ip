package lia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTodoTaskAndGetListSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());

        taskList.addTodoTask("Test Task 1");
        assertEquals(1, taskList.getSize());

        taskList.addTodoTask("Test Task 2");
        assertEquals(2, taskList.getSize());
    }
}
