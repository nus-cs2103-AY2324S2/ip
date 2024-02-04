package ken.task;

import ken.exception.KenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTodoTask_validDescription_success() throws KenException {
        taskList.addTodoTask("Buy groceries");
        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(new Todo("Buy groceries").getDescription(), tasks.get(0).getDescription());
    }

    @Test
    public void addTodoTask_emptyDescription_exceptionThrown() {
        assertThrows(KenException.class, () -> taskList.addTodoTask(""));
    }

    @Test
    public void addDeadlineTask_validDescription_success() throws KenException {
        taskList.addDeadlineTask("Submit report /by 2023-12-31 0000");
        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(new Deadline("Submit report", "2023-12-31 0000").getDescription(), tasks.get(0).getDescription());
    }

    @Test
    public void addDeadlineTask_invalidDescription_exceptionThrown() {
        assertThrows(KenException.class, () -> taskList.addDeadlineTask("Invalid deadline command"));
    }

    @Test
    public void addEventTask_validDescription_success() throws KenException {
        taskList.addEventTask("Birthday party /from 2023-01-01 0000 /to 2023-01-02 0000");
        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(new Event("Birthday party", "2023-01-01 0000", "2023-01-02 0000").getDescription(), tasks.get(0).getDescription());
    }
}
