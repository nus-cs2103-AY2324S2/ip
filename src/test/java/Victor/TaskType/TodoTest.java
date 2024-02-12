package victor.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import victor.tasklist.TaskList;


public class TodoTest {

    @Test
    public void firstToDoTest() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("Todo Test 1", false);

        tasks.addTask(todoTask);

        assertEquals(tasks.getPosValue(0), todoTask);
        assertEquals("[T][ ] Todo Test 1", todoTask.toString());
    }
}
