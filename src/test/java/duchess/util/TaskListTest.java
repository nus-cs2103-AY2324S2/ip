package duchess.util;

import duchess.tasks.Task;
import duchess.tasks.ToDo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addToDoTest(){
        TaskList list = new TaskList();
        Task t = new ToDo("toDoThis");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(t);
        list.createTask(t);
        assertEquals(arr, list.retrieveTaskList());
    }
}
