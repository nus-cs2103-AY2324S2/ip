package arona;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTask_success() {
        try {
            Ui ui = new Ui();
            TaskList taskList = new TaskList(ui);
            String fullCommand = "deadline movie /by 2020-12-31";
            taskList.addTask(fullCommand);
            assertEquals("[D][ ] movie (by: 31/12/2020)", taskList.getTasks().get(0).toString());
        } catch (TaskException e) {

        }
    }

    @Test
    public void addTask_exceptionThrown() {
        try {
            Ui ui = new Ui();
            TaskList taskList = new TaskList(ui);
            String fullCommand = "deadline /by 2020-12-31";
            taskList.addTask(fullCommand);
            fail();
        } catch (TaskException e) {
            assertEquals("Sensei! Please provide some task description!", e.getMessage());
        }
    }

    @Test
    public void changeTaskStatus_success() {
        try {
            Ui ui = new Ui();
            TaskList taskList = new TaskList(ui);
            String fullCommand = "deadline movie /by 2020-12-31";
            taskList.addTask(fullCommand);
            taskList.changeTaskStatus(1, true);
            assertEquals("[D][X] movie (by: 31/12/2020)", taskList.getTasks().get(0).toString());
        } catch (TaskException e) {

        }
    }

    @Test
    public void changeTaskStatus_exceptionThrown() {
        try {
            Ui ui = new Ui();
            TaskList taskList = new TaskList(ui);
            String fullCommand = "deadline movie /by 2020-12-31";
            taskList.addTask(fullCommand);
            taskList.changeTaskStatus(1, true);
            assertEquals("[D][X] movie (by: 31/12/2020)", taskList.getTasks().get(1).toString());
        } catch (TaskException e) {

        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }
}
