package duke.utility;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void markTask_validTodoTaskInput_success() {
        TaskList testList = new TaskList();
        testList.addTask(new ToDo("test todo"));
        testList.markTask(0);
        assertEquals(testList.getTaskStore().get(0).toString(), "[T][X] test todo" );
    }

    @Test
    public void deletedTask_validTask_success() {
        TaskList testList = new TaskList();
        testList.addTask(new ToDo("test1"));
        testList.addTask(new ToDo("test2"));
        testList.deleteTask(1);
        testList.deleteTask(0);
        assertEquals(0, testList.listSize());
    }
}
