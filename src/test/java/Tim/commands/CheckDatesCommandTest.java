package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.parser.Parser;
import Tim.storage.TaskList;
import Tim.task.Deadline;
import Tim.task.Event;
import Tim.task.ToDo;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CheckDatesCommandTest {

    private Path writeToFile;

    @Test
    public void testExecute() throws TimException {
        Path filePath = Paths.get("./data/test.txt");
        Deadline deadline1 = new Deadline("Test", LocalDate.of(2022, 1, 1));
        Event event = new Event("Test", LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 3, 1));
        Deadline deadline2 = new Deadline("Test", LocalDate.of(2020, 1, 1));
        ToDo todo = new ToDo("Test");
        TaskList output = new TaskList();
        TaskList input = new TaskList();
        output.add(deadline1);
        output.add(event);
        input.add(deadline1);
        input.add(event);
        input.add(todo);
        LocalDate fromDate = Parser.getDate("1/1/2022");
        LocalDate toDate = Parser.getDate("1/1/2023");
        CheckDatesCommand command = new CheckDatesCommand(fromDate, toDate, filePath);
        String expectedOutput = GUI.showTaskInScheduleMsg(output);
        // Asserts output is only deadline and event tasks
        assertEquals(command.execute(input), expectedOutput);
        input.add(deadline2);
        output.add(deadline2);
        expectedOutput = GUI.showTaskInScheduleMsg(output);
        // Asserts output is only task that is in the date range
        assertNotEquals(command.execute(input), expectedOutput);

    }
}
