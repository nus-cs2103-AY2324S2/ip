package remi.model.commands;

import org.junit.jupiter.api.Test;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandListTest {
    @Test
    public void addTodo_NoError() {
        Ui dummyChatBot = new Ui();
        TaskList dummyTaskList = new TaskList();
        CommandList commandList = new CommandList(dummyTaskList, dummyChatBot);

        assertDoesNotThrow(() -> commandList.runKeyword("todo", "stuff"));
    }

    @Test
    public void nonsenseCommand_ShouldThrowError() {
        Ui dummyChatBot = new Ui();
        TaskList dummyTaskList = new TaskList();
        CommandList commandList = new CommandList(dummyTaskList, dummyChatBot);

        assertThrows(RemiError.class, () -> commandList.runKeyword("nonsense", ""));
    }
}
