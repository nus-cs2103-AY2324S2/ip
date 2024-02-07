package victor.tasktype;

import org.junit.jupiter.api.Test;
import victor.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineTest() {
        TaskList tasks = new TaskList();
        Task deadlineTask = new Deadline("Deadline Test 1", false, "2019-03-03");

        tasks.addTask(deadlineTask);

        assertEquals(tasks.getPosValue(0),deadlineTask);
        assertEquals("[D][ ] Deadline Test 1 (by: Mar 03 2019)",deadlineTask.toString());
    }
}
