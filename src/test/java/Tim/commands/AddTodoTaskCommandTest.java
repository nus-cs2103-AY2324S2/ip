package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.ToDo;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoTaskCommandTest {
    @Test
    public void testExecute() throws TimException {
        ToDo task = new ToDo("test");
        TaskList taskList = new TaskList();
        Path filePath = Paths.get("./data/test.txt");
        AddTodoTaskCommand command = new AddTodoTaskCommand(task, filePath);
        String output = command.execute(taskList);
        String expectedOutput = GUI.showAddMsg(task, taskList.size());
        assertEquals(output, expectedOutput);

    }

}
