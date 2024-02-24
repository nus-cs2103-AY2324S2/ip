package virtue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class VirtueTaskListTest {
    @Test
    public void numTasksTest() {
        VirtueTaskList taskList = new VirtueTaskList();
        assertEquals(taskList.numTasks(), 0);
        Command command;

        try {
            command = new Command("event Possible /from 2024-08-23T12:00:00 /to 2024-08-24T08:24:00");
            taskList.applyCommand(command);
            assertEquals(taskList.numTasks(), 1);
            command = new Command("delete 1");
            taskList.applyCommand(command);
            assertEquals(taskList.numTasks(), 0);
        } catch (VirtueException e) {
            fail();
        }
    }

    @Test
    public void addFromFileTest() {
        VirtueTaskList taskList = new VirtueTaskList();

        try {
            taskList.addFromFile("D | 1 | Bring the goods  | 2025-02-13T12:00");
            String expectedFileFormat = "D | 1 | Bring the goods  | 2025-02-13T12:00";
            assertEquals(taskList.fileFormat(), expectedFileFormat);
        } catch (VirtueException e) {
            fail();
        }
    }
}
