package duke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private final String testFilePath = "./data/testLogfile.txt";
    @BeforeEach
    void setUp() throws IOException {
        File testFile = new File(testFilePath);
        testFile.getParentFile().mkdirs();
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.createNewFile();
        storage = new Storage(testFilePath);
        ui = new Ui();
        taskList = new TaskList(storage, ui);
    }

    @Test
    void testAddTodoTaskIncreasesListSizeByOne() throws IOException {
        int initialSize = taskList.getSize();
        Task todoTask = new ToDo("Test Todo Task");
        taskList.addTodoTask(todoTask);
        int newSize = taskList.getSize();

        assertEquals(initialSize + 1, newSize);
    }

    @Test
    void testMarkTaskAsDone() throws IOException {
        Task todoTask = new ToDo("Sample Todo Task");
        taskList.addTodoTask(todoTask);
        int taskIndex = taskList.getSize() - 1;

        taskList.markTask(taskIndex);

        Task markedTask = (Task) taskList.getTask(taskIndex);
        assertTrue(markedTask.isDone());

    }

    @Test
    void testDeleteTask() throws IOException {
        Task task1 = new ToDo("Sample ToDo 1");
        Task task2 = new ToDo("Sample ToDo 2");
        taskList.addTodoTask(task1);
        taskList.addTodoTask(task2);
        int initialSize = taskList.getSize();

        taskList.deleteTask(1);
        int newSize = taskList.getSize();

        assertEquals(initialSize - 1, newSize);

        assertFalse(taskList.getTask(0).equals(task1));
    }
}
