package lex.parser.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import lex.tasks.TaskList;
import lex.tasks.Todo;
import lex.ui.Ui;

public class TodoCommandTest {

    @Test
    public void executeTest() throws Exception {
        String[] inputs = {"todo", "Task 1"};
        var mockTasks = mock(TaskList.class);
        var mockUi = mock(Ui.class);
        var command = new TodoCommand(inputs, mockTasks, mockUi);

        when(mockTasks.size()).thenReturn(1);
        when(mockTasks.get(0)).thenReturn(new Todo("Task 1"));

        ArgumentCaptor<String> printCaptor = ArgumentCaptor.forClass(String.class);

        var exit = command.execute();

        verify(mockUi, times(3)).print(printCaptor.capture());
        assertEquals("Got it. I've added this task:", printCaptor.getAllValues().get(0));
        assertEquals("  [T][ ] Task 1", printCaptor.getAllValues().get(1));
        assertEquals("Now you have 1 tasks in the list.", printCaptor.getAllValues().get(2));

        assertFalse(exit);
    }

    @Test
    public void executeExceptionTest() {
        String[] inputs = {"todo"}; // Missing task description.
        var mockTasks = mock(TaskList.class);
        var mockUi = mock(Ui.class);
        var command = new TodoCommand(inputs, mockTasks, mockUi);

        Assertions.assertThrows(Exception.class, command::execute);
    }
}
