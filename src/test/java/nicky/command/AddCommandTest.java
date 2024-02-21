package nicky.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.Deadline;
import nicky.task.Event;
import nicky.task.Storage;
import nicky.task.TaskList;
/**
 * This class contains JUnit tests for the AddCommand class in the Nicky application.
 */
class AddCommandTest {
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
    void addTodo_emptyDescription_throwsNickyException() {
        AddCommand command = new AddCommand("todo ");
        NickyException exception = assertThrows(NickyException.class, () -> command.execute(tasks, ui, storage));
        String expectedMessage = "The description cannot be empty.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addDeadline_validDateTime_addsDeadline() throws NickyException, IOException {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12T12:00");
        String result = command.execute(tasks, ui, storage);
        Assertions.assertTrue(result.contains("Got it. I've added this task"));
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTask(0));
    }

    @Test
    void addDeadline_invalidDateTime_throwsNickyException() {
        AddCommand command = new AddCommand("deadline return book /by 2022-12-12");
        NickyException exception = assertThrows(NickyException.class, () -> command.execute(tasks, ui, storage));
        String expectedMessage = "Invalid date-time format. "
                + "Please use a valid format such as yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addEvent_validEvent_addsEvent() throws NickyException, IOException {
        AddCommand command = new AddCommand("event team meeting /from 2022-12-12 10:00 /to 2022-12-12 12:00");
        String result = command.execute(tasks, ui, storage);
        Assertions.assertTrue(result.contains("Got it. I've added this task"));
        assertInstanceOf(Event.class, tasks.getTask(0));
    }

    @Test
    void addEvent_invalidEventTime_throwsNickyException() {
        AddCommand command = new AddCommand("event team meeting /from 2022-12-12 10:00");
        assertThrows(NickyException.class, () -> command.execute(tasks, ui, storage));
    }
}
