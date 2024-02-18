package luke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser(new Storage(), null);
    String correctMessageForTask = "I helped you add task '[T][ ] stuff'. But do it yourself next time! Hmmph!\n";
    String correctMessageForDeadline = "I helped you add task '[D][ ] homework (by: 2001-08-27)'. But do it yourself next time! Hmmph!\n";
    String correctMessageForEvent = "I helped you add task '[E][ ] holiday (from: 2001-08-27 to: 2001-08-28)'. But do it yourself next time! Hmmph!\n";

    @Test
    public void makeTask_correctParams_returnTaskString() throws TasklistException, ParseCommandException {
        assertEquals(correctMessageForTask, parser.parseCommand("todo stuff"));
        assertEquals(correctMessageForDeadline, parser.parseCommand("deadline homework / by 27-08-2001"));
        assertEquals(correctMessageForEvent, parser.parseCommand("event holiday / from 27-08-2001 / to 28-08-2001"));
    }

    @Test
    public void makeTask_weirdSpacing_returnTaskString() throws TasklistException, ParseCommandException {
        assertEquals(correctMessageForTask, parser.parseCommand("todo          stuff"));
        assertEquals(correctMessageForDeadline, parser.parseCommand("deadline  homework   /   by    27-08-2001"  ));
        assertEquals(correctMessageForEvent, parser.parseCommand("  event holiday / from    27-08-2001 /  to 28-08-2001"  ));
    }

    @Test
    public void makeTask_unknownCommand_returnUnknownTaskString() throws TasklistException, ParseCommandException {
        String unknownTaskString = "/// What on earth are you saying! ///\n"
                + "[Command not found]\n";
        assertEquals(unknownTaskString, parser.parseCommand("akjdijbakf"));
    }
}
