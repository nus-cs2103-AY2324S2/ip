package duchess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void addToDoTest(){
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo("todo buy groceries");
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, taskList.getTasks().size());
        assertEquals("[T][ ] buy groceries", taskList.getTasks().get(0).toString());
    }
}