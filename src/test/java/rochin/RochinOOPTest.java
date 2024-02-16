package rochin;

import org.junit.jupiter.api.Test;
import rochinoop.RochinOOP;

import static org.junit.jupiter.api.Assertions.*;

public class RochinOOPTest {


    @Test
    public void testProcessTodoCommand() {
        RochinOOP rochinOOP = new RochinOOP();
        RochinOOP.CommandProcessor commandProcessor = rochinOOP.new CommandProcessor("todo Test Todo Task");
        RochinOOP.TaskList taskList = rochinOOP.new TaskList();
        RochinOOP.Ui ui = rochinOOP.new Ui();

        commandProcessor.processTodoCommand(taskList, ui);
        assertEquals(1, taskList.getAllTasks().size());
    }

}
