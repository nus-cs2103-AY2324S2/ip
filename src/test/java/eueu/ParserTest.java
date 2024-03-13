package eueu;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @Test
    public void write_nonCommand() throws IOException {
        File f = new File("data/EUEU.txt");
        Storage s = new Storage(f);
        TaskList tasklist = new TaskList(s);
        Parser parse = new Parser(tasklist);
        String nonCommandResponse = "Baby, what are you saying? " +
                "Tell me what your TODOs, DEADLINEs and EVENTs are!";
        assertEquals(nonCommandResponse, parse.parsing("jkdasbfka"));
    }

    @Test
    public void write_invalidTaskInput() throws IOException {
        File f = new File("data/EUEU.txt");
        Storage s = new Storage(f);
        TaskList tasklist = new TaskList(s);
        Parser parse = new Parser(tasklist);
        String invalidTaskResponse = "ENTER TASK";
        assertEquals(invalidTaskResponse, parse.parsing("todo"));
    }

    @Test
    public void write_invalidDeadlineInput() throws IOException {
        File f = new File("data/EUEU.txt");
        Storage s = new Storage(f);
        TaskList tasklist = new TaskList(s);
        Parser parse = new Parser(tasklist);
        String invalidDeadlineResponse = "Let me know your deadlines babe: e.g. deadline <deadline> /by <ddl>";
        assertEquals(invalidDeadlineResponse, parse.parsing("deadline work"));
    }

    @Test
    public void write_invalidEventInput() throws IOException {
        File f = new File("data/EUEU.txt");
        Storage s = new Storage(f);
        TaskList tasklist = new TaskList(s);
        Parser parse = new Parser(tasklist);
        String invalidEventResponse = "Let me know when this event is bb: " +
                "e.g. event <event> /from <when>/to <when>";
        assertEquals(invalidEventResponse, parse.parsing("event work"));
    }
}
