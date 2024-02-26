package chatbot.task;

import chatbot.exception.DukeException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList tasks;
    private Storage storage;
    private static final String TEST_PATH_FILE = "./out/test.txt";


    @BeforeEach
    public void setUp() throws DukeException {
        this.tasks = new TaskList();
        this.storage = new Storage(TEST_PATH_FILE);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_PATH_FILE);
        file.delete();
    }

    @Test
    public void addTodoTask_success() {
        tasks.addTodoTask("add todo task test");
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void addDeadlineTask_success() {
        LocalDateTime deadline = LocalDateTime.now().plusDays(1);
        tasks.addDeadlineTask("add deadline task test", deadline);
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void addEventTask_success() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);
        tasks.addEventTask("add event task test", start, end);
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void testDeleteTask_success() throws DukeException {
        tasks.addTodoTask("todo task");
        tasks.deleteTask(1);
        assertEquals(0, tasks.getNumOfTasks());
    }

    @Test
    public void testDeleteTask_exceptionThrown() {
        try {
            tasks.deleteTask(0);
            fail();
        } catch (DukeException e) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error: there's no such thing as task 0!\n"
                    + Ui.createLine();
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void testMarkTask_success() throws DukeException {
        tasks.addTodoTask("todo task");
        tasks.markTask(1);
        assertTrue(tasks.printTask(1).contains("[X]"));
    }

    @Test
    public void testMarkTask_exceptionThrown() {
        try {
            tasks.markTask(0);
            fail();
        } catch (DukeException e) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "task 0? how can i mark a task that doesn't exist?!\n"
                    + Ui.createLine();
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void testUnmarkTask_success() throws DukeException {
        tasks.addTodoTask("todo task");
        tasks.markTask(1);
        tasks.unmarkTask(1);
        assertFalse(tasks.printTask(1).contains("[X]"));
    }

    @Test
    public void testUnmarkTask_exceptionThrown() throws DukeException {
        try {
            tasks.unmarkTask(1);
            fail();
        } catch (DukeException e) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "hahaha! you only have 0 tasks in your task list!!\n"
                    + "there's no task 1!\n"
                    + Ui.createLine();
            assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
