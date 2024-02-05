package duke.task;

import duke.IOHandler;
import duke.Parser;
import duke.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Storage storage = new Storage();
    IOHandler ioHandler = new IOHandler();

    TaskList List = new TaskList();


    @Test
    public void IdentifyTestWithCommand() {
        Parser parser = new Parser();
        String command = parser.identify("deadline return book /by 2023-03-03 1220");
        assertEquals("deadline", command);
        command = parser.identify("event attend marraige /from 2023-09-09 1220 /to 2023-09-10 1220");
        assertEquals("event", command);
        command = parser.identify("todo project");
        assertEquals("todo", command);
    }



}