import exceptions.JojoUnknownTaskException;
import jojo.Parser;
import jojo.TaskList;
import jojo.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParseEvent() {
        String cmd = "event Birthday party /from 2024-03-22 12:00 /to 2024-03-22 14:00";
        String[] eventDetails = Parser.parseEvent(cmd);
        assertEquals("Birthday party", eventDetails[0]);
        assertEquals("2024-03-22 12:00", eventDetails[1]);
        assertEquals("2024-03-22 14:00", eventDetails[2]);
    }

    @Test
    public void testparseDeadlineBy() {
        String cmd = "CS2103T ip /by 09-08-2024 23:59";
        LocalDateTime ldt = Parser.parseDeadlineBy(cmd);
        assertEquals(LocalDateTime.of(2024, 8, 9, 23, 59), ldt);
    }

    @Test
    public void testParseUnknownTask() {
        String cmd = "unknown-task";
        assertThrows(JojoUnknownTaskException.class, () -> Parser.parse(cmd, new Ui(), new TaskList(new ArrayList<>())));
    }

}
