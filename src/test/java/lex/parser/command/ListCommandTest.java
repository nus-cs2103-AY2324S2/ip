package lex.parser.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import lex.tasks.Deadline;
import lex.tasks.TaskList;
import lex.tasks.Todo;
import lex.ui.Ui;

public class ListCommandTest {
    @Test
    public void executeTest() {
        var mockTasks = mock(TaskList.class);
        var mockUi = mock(Ui.class);
        var command = new ListCommand(mockTasks, mockUi);

        when(mockTasks.size()).thenReturn(2);
        when(mockTasks.get(0)).thenReturn(new Todo("Task 1"));
        when(mockTasks.get(1)).thenReturn(new Deadline("Task 2", LocalDate.parse("2024-08-24")));

        ArgumentCaptor<String> printCaptor = ArgumentCaptor.forClass(String.class);

        var exit = command.execute();

        verify(mockUi, times(2)).print(printCaptor.capture());
        assertEquals("1. [T][ ] Task 1", printCaptor.getAllValues().get(0));
        assertEquals("2. [D][ ] Task 2 (by: Aug 24 2024)", printCaptor.getAllValues().get(1));

        assertFalse(exit);
    }
}
