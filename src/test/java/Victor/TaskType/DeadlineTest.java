package victor.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import victor.tasklist.TaskList;



public class DeadlineTest {
    @Test
    public void firstDeadlineTest() {
        TaskList tasks = new TaskList();
        Task deadlineTask = new Deadline("Deadline Test 1", false, "2019-03-03");

        tasks.addTask(deadlineTask);

        assertEquals(tasks.getPosValue(0), deadlineTask);
        assertEquals("[D][ ] Deadline Test 1 (by: Mar 03 2019)", deadlineTask.toString());
    }
}
