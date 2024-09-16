package duke;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        ui = new Ui();
        storage = new Storage("testData.txt");
        taskList = new TaskList(storage, ui);
    }
    @Test
    void testProcessAddTodoCommand() throws DukeException, IOException {
        Parser parser = new Parser(ui, storage, taskList);
        int initialSize = taskList.getSize();

        String todoCommand = "todo Read a book";
        parser.processCommand(todoCommand);

        assertEquals(initialSize + 1, taskList.getSize());
    }

    @Test
    public void testSetDeadline() throws IOException {

        int initialSize = taskList.getSize();
        String description = "Return book";
        LocalDateTime endTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        Task deadline = new Deadline(description, endTime);
        taskList.addDeadlineTask(deadline);

        assertEquals(initialSize + 1, taskList.getSize());

        Task addedTask = (Task) taskList.getTask(initialSize);
        assertTrue(addedTask instanceof Deadline);
        Deadline addedDeadline = (Deadline) addedTask;
        assertEquals(description, addedDeadline.getDESCRIPTION());
        assertEquals(endTime, addedDeadline.getEndTime());
    }

    @Test
    public void testSetEvent() throws IOException {
        int initialSize = taskList.getSize();

        String description = "Project meeting";
        LocalDate startDate = LocalDate.of(2019, 12, 15);
        LocalDate endDate = LocalDate.of(2019, 12, 15);
        Task event = new Event(description, startDate, endDate);
        taskList.addEventTask(event);

        assertEquals(initialSize + 1, taskList.getSize());

        Task addedTask = (Task) taskList.getTask(initialSize);
        assertTrue(addedTask instanceof Event);
        Event addedEvent = (Event) addedTask;
        assertEquals(description, addedEvent.getDESCRIPTION());
        assertEquals(startDate, addedEvent.getStartTime());
        assertEquals(endDate, addedEvent.getEndTime());
    }



}

