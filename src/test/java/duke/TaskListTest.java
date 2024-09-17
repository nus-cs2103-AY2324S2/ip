package duke;

        import duke.task.Task;
        import duke.task.ToDo;
        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read book");
        taskList.addTask(task);

        assertEquals(1, taskList.getTasksSize()); // Assuming getTasksSize() returns the number of tasks
        assertEquals(task, taskList.getTask(0)); // Assuming getTask(0) returns the first task
    }
}
