package duke;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testBasics() {
        assertEquals(new TaskList(), new TaskList());
    }

    @Test
    public void testAdd() {
        TaskList list = new TaskList();
        Task task = new ToDo("hello!");
        list.add(task);
        assertEquals(list.get(0), task);
    }

    @Test
    public void testRemove() {
        TaskList list = new TaskList();
        Task task = new ToDo("hello!");
        list.add(task);
        list.remove(0);
        assertEquals(list.get(0), new TaskList());
    }

    @Test
    public void testIterate() {
        TaskList list = new TaskList();
        Task task1 = new ToDo("1");
        Task task2 = new ToDo("1");
        Task task3 = new ToDo("1");
        list.add(task1);
        list.add(task2);
        list.add(task3);
        Task[] tasks = {task1, task2, task3};
        int idx = 0;
        for (Task inList: list) {
            assertEquals(inList, tasks[idx]);
            idx++;
        }
    }


}
