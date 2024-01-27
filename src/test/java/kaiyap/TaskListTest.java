package kaiyap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    //KaiYap object for testing
    private KaiYap yap;

    @BeforeEach
    public void setUp() {
        yap = new KaiYap();
    }

    @Test
    public void testAddTaskSuccessfully() {
        Task newTask = yap.taskList.taskCreator("todo Complete Optiver OA");
        yap.taskList.add(newTask);
        yap.storage.saveData();
        assertEquals(1, yap.taskList.size());
        assertEquals(newTask, yap.taskList.get(0));
    }

    // Additional tests can be added for specific business rules, like preventing duplicates
}
