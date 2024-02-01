package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        TaskList.create();
    }

    @Test
    void markTask_validIndex_taskMarked() {
        Task task = new ToDo("test task");
        TaskList.addTaskSlient(task);
        TaskList.markTask(1);
        assertTrue(task.isDone());
    }

    @Test
    void markTask_alreadyMarked_noEffect() {
        Task task = new ToDo("test task");
        TaskList.addTaskSlient(task);
        TaskList.markTask(1);
        assertTrue(task.isDone());
        TaskList.markTask(1);
        assertTrue(task.isDone());
    }

    @Test
    void markTask_invalidIndex_throwsIndexOutOfBoundsException() {
        Task task = new ToDo("test task");
        TaskList.addTaskSlient(task);
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.markTask(2));
    }

    @Test
    void markTask_negativeIndex_throwsIndexOutOfBoundsException() {
        Task task = new ToDo("test task");
        TaskList.addTaskSlient(task);
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.markTask(-1));
    }

    @Test
    void markTask_zeroIndex_throwsIndexOutOfBoundsException() {
        Task task = new ToDo("test task");
        TaskList.addTaskSlient(task);
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.markTask(0));
    }
}