package parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test class for Parser.
 */
public class ParserTest {

    /**
     * Test for parsing "bye" command.
     */
    @Test
    public void parseByeTest() {
        assertEquals("bye", new ParserStub().parseStub("bye"));
    }

    /**
     * Test for parsing "list" command.
     */
    @Test
    public void parseListTest() {
        assertEquals("list", new ParserStub().parseStub("list"));
    }

    /**
     * Test for parsing "getcommands" command.
     */
    @Test
    public void parseGetCommandsTest() {
        assertEquals("getcommands",
                new ParserStub().parseStub("getcommands"));
    }

    /**
     * Test for parsing "mark" command.
     */
    @Test
    public void parseMarkTest() {
        assertEquals("mark 5", new ParserStub().parseStub("mark 5"));
        assertEquals("Brother, key in mark <space> then a valid number",
                new ParserStub().parseStub("mark"));
        assertEquals("You tell me now what task am I supposed to mark if you don't provide me with a number?",
                new ParserStub().parseStub("mark cc"));
    }

    /**
     * Test for parsing "unmark" command.
     */
    @Test
    public void parseUnmarkTest() {
        assertEquals("unmark 3", new ParserStub().parseStub("unmark 3"));
        assertEquals("Brother, key in unmark <space> then a valid number",
                new ParserStub().parseStub("unmark"));
        assertEquals("You tell me now what task am I supposed to unmark if you don't provide me with a number?",
                new ParserStub().parseStub("unmark cc"));
    }

    /**
     * Test for parsing "todo" command.
     */
    @Test
    public void parseTodoTest() {
        assertEquals("todo eat", new ParserStub().parseStub("todo eat"));
        assertEquals("Help la, can just tell me what is the name of your task anot?",
                new ParserStub().parseStub("todo   "));
        assertEquals("You trying to test my patience ah? "
                + "Type \"get commands\" if u blur and dunno how to use me properly.",
                new ParserStub().parseStub("todo"));
    }

    /**
     * Test for parsing "deadline" command.
     */
    @Test
    public void parseDeadlineTest() {
        assertEquals("deadline homework 03-16-2024 1500",
                new ParserStub().parseStub("deadline homework / 03-16-2024 1500"));
        assertEquals("Help la, can just tell me what is the name of your task anot?",
                new ParserStub().parseStub("deadline  "));
        assertEquals("You trying to test my patience ah? Check that u got key in the deadline lehhh\n"
                + "Type \"get commands\" if u blur and dunno how to use me properly.",
                new ParserStub().parseStub("deadline "));
    }

    /**
     * Test for parsing "event" command.
     */
    @Test
    public void parseEventTest() {
        assertEquals("event dinner 03-17-2024 1800 03-17-2024 2000",
                new ParserStub().parseStub("event dinner / 03-17-2024 1800 / 03-17-2024 2000"));
        assertEquals("Help la, can just tell me what is the name of your task anot?",
                new ParserStub().parseStub("event dinner / / "));
        assertEquals("Eh brother last warning ah. Check that u got key in the start and end time\n"
                + "Type \"get commands\" if u blur and dunno how to use me properly.",
                new ParserStub().parseStub("event sleep"));
    }

    /**
     * Test for parsing "delete" command.
     */
    @Test
    public void parseDeleteTest() {
        assertEquals("delete 1", new ParserStub().parseStub("delete 1"));
        assertEquals("Brother, key in delete <space> then a valid number",
                new ParserStub().parseStub("delete"));
        assertEquals("You tell me now what task am I supposed to delete"
                        + " if you don't provide me with a number?",
                new ParserStub().parseStub("delete cc"));
    }

    /**
     * Tests the parsing of the "find" command.
     * Checks if the parser correctly handles input with and without additional keywords.
     */
    @Test
    public void parseFindTest() {
        // Tests parsing when additional keywords are provided
        assertEquals("find time", new ParserStub().parseStub("find time"));
        // Tests parsing when no additional keywords are provided
        assertEquals("What u want me to find??", new ParserStub().parseStub("find"));
    }
}
