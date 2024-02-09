package TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    TaskList taskList;
    public TaskListTest() {
        this.taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            this.taskList.addTask(new TaskStub(i));
        }
    }

    @Test
    public void getTaskTest_success() {
        assertEquals("[ ] TaskStub0", this.taskList.getTask(0).toString());
        assertEquals("[ ] TaskStub2", this.taskList.getTask(2).toString());
        assertEquals("[ ] TaskStub5", this.taskList.getTask(5).toString());
        assertEquals("[ ] TaskStub7", this.taskList.getTask(7).toString());
    }

    @Test
    public void getTaskTest_error() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.taskList.getTask(12);
        });
    }

    @Test
    public void taskSizeTest_success() {
       assertEquals(this.taskList.size(), 10);
       this.taskList.clear();
        assertEquals(this.taskList.size(), 0);
    }
}
