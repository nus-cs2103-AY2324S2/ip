package pingmebot;

import org.junit.jupiter.api.Test;
import pingmebot.task.Deadline;
import pingmebot.task.Events;
import pingmebot.task.ToDos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void todoParserTest() throws myBotException {
        String command = "todo project";
        Parser parser = new Parser(command);
        assertEquals(new ToDos("project"),parser.todoParser());
    }

    @Test
    public void deadlineParserTest() throws myBotException {
        String command = "deadline project /by 05/05/2000 1800";
        Parser parser = new Parser(command);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String time = "05/05/2000 1800";
        assertEquals(new Deadline("project", LocalDateTime.parse(time, formatter)),parser.deadlineParser());
    }

    @Test
    public void eventParserTest() throws myBotException {
        String command  = "event project /from 9am /to 8pm";
        Parser parser = new Parser(command);
        assertEquals(new Events("project", " 9am"," 8pm"),parser.eventsParser());
    }

    @Test
    public void markParserTest() throws myBotException {
        String command = "mark 2";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command);
        assertEquals(1, parser.markParser(arbituaryNumOfTask));
    }

    @Test
    public void unmarkParserTest() throws myBotException {
        String command = "unmark 2";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command);
        assertEquals(1, parser.markParser(arbituaryNumOfTask));
    }

    @Test
    public void deleteParserTest() throws myBotException {
        String command = "delete 2";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command);
        assertEquals(1, parser.markParser(arbituaryNumOfTask));
    }
}
