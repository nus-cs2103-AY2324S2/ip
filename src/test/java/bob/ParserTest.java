package bob;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void test1() {
        TaskList expected = new TaskList();
        expected.addTask(new ToDo("make dinner"));

        TaskList actual = new TaskList();
        String input = "todo make dinner";
        Parser parser = new Parser(new Ui());
        parser.parseTodo(input, actual);

        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        TaskList expected = new TaskList();
        String deadlineDate = "2001-07-24 0000";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(deadlineDate, formatter);
        expected.addTask(new Deadline("make dinner", dateTime));

        TaskList actual = new TaskList();
        String input = "deadline make dinner /by 2001-07-24 0000";
        Parser parser = new Parser(new Ui());
        parser.parseDeadline(input, actual);

        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        TaskList expected = new TaskList();
        String description = "make dinner";
        String fromDate = "Sunday 2am";
        String toDate = "3am";
        expected.addTask(new Event(description, fromDate, toDate));

        TaskList actual = new TaskList();
        String input = "event make dinner /from Sunday 2am /to 3am";
        Parser parser = new Parser(new Ui());
        parser.parseEvent(input, actual);

        assertEquals(expected, actual);
    }
}
