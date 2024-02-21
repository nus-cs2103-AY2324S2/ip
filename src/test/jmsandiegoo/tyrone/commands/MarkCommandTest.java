package jmsandiegoo.tyrone.commands;

import jmsandiegoo.tyrone.common.Messages;
import jmsandiegoo.tyrone.exceptions.CommandExecutionException;
import jmsandiegoo.tyrone.task.Task;
import jmsandiegoo.tyrone.task.TaskList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MarkCommandTest {
    @Mock
    private TaskList mockTaskList;
    @Mock
    private Task mockTask;
    @Mock
    private CommandResult mockCommandResult;

    @Test
    public void execute_validIndex_success() {
        when(mockTask.toString()).thenReturn("marked item");
        when(mockTaskList.getItem(eq(1))).thenReturn(mockTask);
        doNothing().when(mockTaskList).markItemDone(eq(1));
        when(mockCommandResult.toString()).thenReturn(
                String.format(Messages.MESSAGE_MARK, "marked item")
        );

        // execute with valid index
        MarkCommand markCommand = new MarkCommand(1);
        markCommand.initData(mockTaskList);
        try {
            assertEquals(markCommand.execute().toString(), mockCommandResult.toString());
        } catch (CommandExecutionException e) {
            fail();
        }
    }

    @Test
    public void execute_outOfRangeIndex_failure() {
        doThrow(new IndexOutOfBoundsException()).when(mockTaskList).markItemDone(anyInt());

        // execute with invalid index >= 5
        MarkCommand markCommand = new MarkCommand(6);
        markCommand.initData(mockTaskList);
        try {
            markCommand.execute();
            fail();
        } catch (CommandExecutionException e) {
            // pass;
        }

        // execute with invalid index < 0
        markCommand = new MarkCommand(-1);
        markCommand.initData(mockTaskList);
        try {
            markCommand.execute();
            fail();
        } catch (CommandExecutionException e) {
            // pass
        }
    }
}
