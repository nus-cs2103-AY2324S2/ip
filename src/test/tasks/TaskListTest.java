package tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TaskListTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    void printListShouldPrintList() {
        Todo todo = new Todo("task1");
        List<Task> lst = new ArrayList<>(List.of(todo));
        TaskList tl1 = new TaskList(lst);
        tl1.printList();
        assertEquals("Here are the tasks in your list:" + System.lineSeparator() + "1. "
                + todo, outContent.toString());
    }

    @Test
    void convertTasksToStringForm() {
        Todo todo = new Todo("task1");
        Deadline deadline = new Deadline("task2", "2024-01-01");
        Event event = new Event("task3", "2024-01-01", "2024-01-02");
        List<Task> lst = new ArrayList<>(Arrays.asList(todo, deadline, event));
        TaskList tl = new TaskList(lst);
        List<String> str = new ArrayList<>(Arrays.asList(todo.toSavedString()
                , deadline.toSavedString()
                , event.toSavedString()));
        assertEquals(tl.taskToSavedString(), str);
    }


}