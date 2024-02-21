package nicky.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nicky.Ui;
import nicky.task.Storage;
import nicky.task.TaskList;
import nicky.task.Todo;

/**
 * This class contains JUnit tests for the ListCommand class in the Nicky application.
 */
class ListCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/nicky.txt") {
            @Override
            public void saveTasks(TaskList tasks) {
            }
        };
    }

    @Test
    void execute_emptyTaskList_returnsNoTasksMessage() {
        ListCommand command = new ListCommand();
        String result = command.execute(tasks, ui, storage);
        assertEquals("Your task list is empty.", result);
    }

    @Test
    void execute_nonEmptyTaskList_returnsTasks() {
        tasks.add(new Todo("Read book"));
        tasks.add(new Todo("Write essay"));

        ListCommand command = new ListCommand();
        String result = command.execute(tasks, ui, storage);

        String expectedOutput = "Here are the tasks in your list:\n1. [T][ ] Read book\n2. [T][ ] Write essay";
        assertEquals(expectedOutput, result.trim());
    }
}
