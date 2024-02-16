package nicky.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.Storage;
import nicky.task.TaskList;
import nicky.task.Todo;

/**
 * This class contains JUnit tests for the FindCommand class in the Nicky application.
 */
class FindCommandTest {
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

        tasks.add(new Todo("Read book"));
        tasks.add(new Todo("Write essay"));
        tasks.add(new Todo("Clean room"));
    }

    @Test
    void execute_findKeywordMatchesMultipleTasks_listsAllMatchingTasks() throws NickyException, IOException {
        FindCommand command = new FindCommand("find book");
        String result = command.execute(tasks, ui, storage);
        assertEquals("Here are the matching tasks in your list:\n1. [T][ ] Read book", result);
    }

    @Test
    void execute_findKeywordNoMatch_returnsNoTasksFoundMessage() throws NickyException, IOException {
        FindCommand command = new FindCommand("find dance");
        String result = command.execute(tasks, ui, storage);
        assertEquals("No tasks found with the keyword: dance", result);
    }

    @Test
    void execute_emptyKeyword_throwsNickyException() {
        FindCommand command = new FindCommand("find");
        NickyException exception = assertThrows(NickyException.class, () -> command.execute(tasks, ui, storage));
        assertEquals("Please provide a keyword to search for.", exception.getMessage());
    }
}
