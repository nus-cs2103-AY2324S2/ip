package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    void testAddTask() {
        String tempFilePath = System.getProperty("java.io.tmpdir") + "/duke_test.txt";
        TaskList taskList = new TaskList(tempFilePath);

        Task task = new Task("Test Task", Task.TaskType.TODO);
        assertEquals(0, taskList.getTasks().size());
        taskList.addTask(task);


        assertEquals(1, taskList.getTasks().size());
        assertEquals(task, taskList.getTask(0));
        taskList.deleteTask(0);
    }

}
