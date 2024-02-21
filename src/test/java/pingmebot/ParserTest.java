package pingmebot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import pingmebot.command.*;
import pingmebot.task.Deadline;
import pingmebot.task.Events;
import pingmebot.task.ToDos;



public class ParserTest {
    @Test
    public void parseToDoCommandTest() throws PingMeException {
        String command = "todo project";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        assertEquals(new AddCommand(new ToDos("project")), parser.parseToDoCommand());
    }

    @Test
    public void parseDeadlineCommandTest() throws PingMeException {
        String command = "deadline project /by 05/05/2000 1800";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String time = "05/05/2000 1800";
        assertEquals(new AddCommand(new Deadline("project", LocalDateTime.parse(time, formatter))),
                parser.parseDeadlineCommand());
    }

    @Test
    public void parseEventsCommandTest() throws PingMeException {
        String command = "event project /from 9am /to 8pm";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        assertEquals(new AddCommand(new Events("project", "9am", "8pm")),
                parser.parseEventsCommand());
    }

    @Test
    public void parseMarkCommandTest() throws PingMeException {
        String command = "mark 2";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        assertEquals(new MarkCommand(1),
                parser.parseMarkCommand(arbituaryNumOfTask));
    }

    @Test
    public void parseUnmarkCommandTest() throws PingMeException {
        String command = "unmark 2";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        assertEquals(new UnmarkCommand(1),
                parser.parseUnmarkCommand(arbituaryNumOfTask));
    }

    @Test
    public void parseDeleteCommandTest() throws PingMeException {
        String command = "delete 2";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        assertEquals(new DeleteCommand(1),
                parser.parseDeleteCommand(arbituaryNumOfTask));
    }

    @Test
    public void parseFindCommandTest() throws PingMeException {
        String command = "find book";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        assertEquals(new FindCommand("book"),
                parser.parseFindCommand());
    }

    @Test
    public void parsePostponeCommandTest() throws PingMeException {
        String command = "postpone 1 /from 8pm /to 9pm";
        int arbituaryNumOfTask = 3;
        Parser parser = new Parser(command, arbituaryNumOfTask);
        PostponeCommand pc = new PostponeCommand(0, "8pm", "9pm", null);
        assertEquals(pc, parser.parsePostponeCommand());
    }

}
