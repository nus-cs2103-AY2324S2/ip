package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.action.TaskList;
import duke.task.Task;
import duke.task.ToDo;


public class TaskListTest {

    @Test
    void test_size() {
        TaskList testList = new TaskList();
        testList.addTask(new ToDo("item 1"));
        testList.addTask(new ToDo("item 2"));
        testList.addTask(new ToDo("item 3"));

        assertEquals(3, testList.size());

        testList.deleteTask(1);
        testList.deleteTask(1);
        assertEquals(1, testList.size());
    }

    @Test
    void test_get() {
        TaskList testList = new TaskList();
        Task itemOne = new ToDo("item 1");
        Task itemTwo = new ToDo("item 2");
        Task itemThree = new ToDo("item 3");
        testList.addTask(itemOne);
        testList.addTask(itemTwo);
        testList.addTask(itemThree);

        assertEquals(itemThree, testList.get(2));

        assertEquals(itemTwo, testList.get(1));
    }

    @Test
    void test_deleteTask() {
        TaskList testList = new TaskList();
        Task itemOne = new ToDo("item 1");
        Task itemTwo = new ToDo("item 2");
        testList.addTask(itemOne);
        testList.addTask(itemTwo);

        assertEquals(itemTwo, testList.deleteTask(1));
    }
    @Test
    void test_addTask() {
        TaskList testList = new TaskList();
        Task itemOne = new ToDo("item 1");
        Task itemTwo = new ToDo("item 2");
        testList.addTask(itemOne);

        assertEquals(1, testList.size());
        testList.addTask(itemTwo);

        assertEquals(2, testList.size());
    }
}
