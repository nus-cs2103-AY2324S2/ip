package lilybot.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void checkParseInt() {
        //check parsing integers with extra spaces
        String firstCmd = "remove 10  \t";
        String secondCmd = "mark 2 ";
        assertEquals(10, Parser.parseInt(firstCmd));
        assertEquals(2, Parser.parseInt(secondCmd));
    }

    @Test
    public void checkParseCommand() {
        //Check command parser with extrac spaces
        String firstCmd = "event   \n interview /from 1pm /to 2pm Tue";
        String expectedString = "interview /from 1pm /to 2pm Tue";
        assertEquals("event", Parser.parseCommand(firstCmd)[0]);
        assertEquals(expectedString, Parser.parseCommand(firstCmd)[1]);
    }
}