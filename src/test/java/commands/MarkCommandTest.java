package commands;

import exceptions.HowieException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import task.Deadline;
import task.Task;
import tasklists.TaskList;
import ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkCommandTest {

    @Test
    public void markCommand_validIndex_successful() throws HowieException, IOException {
        Ui ui = new Ui();
        TaskList taskLs = new TaskList();
        taskLs.add(new Task("do chores"));
        taskLs.add(new Deadline("do homework", "Sunday"));
        Command mark1 = new MarkCommand(1);
        Command mark2 = new MarkCommand(2);

        mark1.setData(taskLs);
        mark1.executeCommand();
        assertTrue(taskLs.get(0).getStatus());

        mark2.setData(taskLs);
        mark2.executeCommand();
        assertTrue(taskLs.get(1).getStatus());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 3, 100})
    public void markCommand_invalidIndex_successful(int i) throws IndexOutOfBoundsException, IOException {
        try {
            Ui ui = new Ui();
            TaskList taskLs = new TaskList();
            taskLs.add(new Task("do chores"));
            taskLs.add(new Deadline("do homework", "Sunday"));
            Command mark3 = new MarkCommand(i);
            mark3.setData(taskLs);
            mark3.executeCommand();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!",
                    e.getMessage());
        } catch (HowieException e) {
            throw new RuntimeException(e);
        }
    }
}
