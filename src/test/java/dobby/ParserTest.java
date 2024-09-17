package dobby;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import dobby.DukeException;
import dobby.Parser;
import dobby.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseTodo(String order) throws DukeException {
        String command = "todo reading";
        Parser parser = new Parser();
        assertEquals(new Todo("reading"), parser.parseTodo(command));
    }

    @Test
    public void parseDeadline(String order) throws DukeException {
        String command = "deadline return book /by 2/12/2019 1800";
        Parser parser = new Parser();
        assertEquals(new Todo("deadline return book /by 2/12/2019 1800"), parser.parseTodo(command));
    }
}