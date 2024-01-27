package kaiyap;
import exceptions.AlreadyExistsException;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class KaiYapTest {

    private KaiYap yap;

    @BeforeEach
    public void setUp() {
        yap = new KaiYap();
        Task task1 = yap.taskList.taskCreator("todo finish iP");
        yap.taskList.add(task1);
        yap.storage.saveData();
        Task task2 = yap.taskList.taskCreator("deadline finish tP /by 28/03/2024 2359");
        yap.taskList.add(task2);
        yap.storage.saveData();
    }

    @AfterEach
    public void tearDown() {
        yap = null;
    }

    @Test
    public void testMarkAsDoneSuccessfully() {
        assertDoesNotThrow(() -> yap.markAsDone("mark 1"));
        assertTrue(yap.taskList.get(0).isTaskDone());
    }

    @Test
    public void testMarkAsDoneWithInvalidIndex() {
        assertThrows(InvalidInputException.class, () -> yap.markAsDone("mark 3"));
    }

    @Test
    public void testMarkAsDoneForAlreadyCompletedTask() {
        yap.taskList.get(0).setTaskDone(true);
        assertThrows(AlreadyExistsException.class, () -> yap.markAsDone("mark 1"));
    }
}
