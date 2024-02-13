package Aaron.Task;

import org.junit.jupiter.api.Test;

import aaron.exception.AaronBotException;
import aaron.exception.TaskErrorException;
import aaron.task.TaskList;
import aaron.task.TaskType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.beans.Transient;
public class TaskListTest {
    
    @Test
    public void addEmptyTodoTest() {
        TaskList tasks = new TaskList();
        assertThrows(TaskErrorException.class, () -> tasks.addToList(TaskType.TODO," "));
    }

    @Test
    public void addEmptyDeadlineTest() {
        TaskList tasks = new TaskList();
        assertThrows(TaskErrorException.class,
                 () -> tasks.addToList(TaskType.DEADLINE," "));
    }

    @Test
    public void addEmptyEventTest() {
        TaskList tasks = new TaskList();
        assertThrows(TaskErrorException.class,
                 () -> tasks.addToList(TaskType.EVENT," "));
    }

    @Test 
    public void addToListTest() {
        TaskList tasks = new TaskList();
        try {
            tasks.addToList(TaskType.TODO, "run");
            tasks.addToList(TaskType.DEADLINE, "homework /by 01-01-2024");
            tasks.addToList(TaskType.EVENT, "class /from 01-01-2024 /to 02-01-2024");
        } catch (TaskErrorException e) {
            System.out.println(e);
        }
        assertEquals("[T][ ] | run", tasks.printTask(1));
        assertEquals("[D][ ] | homework | 01-01-2024", tasks.printTask(2));
        assertEquals("[E][ ] | class | 01-01-2024 | 02-01-2024", tasks.printTask(3));
    }

}