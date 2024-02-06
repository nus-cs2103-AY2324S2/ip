package duke.main;
import duke.exception.DateFormatException;
import duke.exception.UnknownInputException;
import duke.task.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TaskList tempList = new TaskList();
    UI tempUI = new UI(tempList);
    Parser tempParse = new Parser(tempList, tempUI);

    @Test
    public void parse_success() throws Exception {
        tempParse.parse("todo read book");
        assertEquals(new ToDo("read book").toString(), tempList.get(0).toString());
    }

    public void parse_unknownInput_exceptionThrown() {
        String errorMessage = "Sorry, I don't know what that command means";
        try {
            tempParse.parse("this is an illegal input");
        } catch (UnknownInputException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
