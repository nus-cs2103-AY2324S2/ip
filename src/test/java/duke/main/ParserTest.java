package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.UnknownInputException;
import duke.task.*;

public class ParserTest {
    private TaskList tempList = new TaskList();
    private NotesList tempNotes = new NotesList();
    private Ui tempUI = new Ui(tempList, tempNotes);
    private Parser tempParse = new Parser(tempList, tempUI, tempNotes);

    @Test
    public void task_parse_success() throws Exception {
        tempParse.parse("todo read book");
        assertEquals(new ToDo("read book").toString(), tempList.get(0).toString());
    }

    public void parse_unknownInput_exceptionThrown() {
        String errorMessage = "Sorry, I don't know what that command means";
        try {
            tempParse.parse("this is an illegal input");
            fail();
        } catch (UnknownInputException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
