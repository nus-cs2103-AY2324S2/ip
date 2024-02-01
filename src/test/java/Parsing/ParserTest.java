package Parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseByeTest() {
        assertEquals("bye", new ParserStub().parseStub("bye"));
    }

    @Test
    public void parseListTest() {
        assertEquals("list", new ParserStub().parseStub("list"));
    }

    @Test
    public void parseGetCommandsTest() {
        assertEquals("getcommands", new ParserStub().parseStub("getcommands"));
    }

    @Test
    public void parseMarkTest() {
        assertEquals("mark 5", new ParserStub().parseStub("mark 5"));
        //catches NumberFormatException if something other than a number is keyed in
        assertEquals("Brother, key in mark <space> then a valid number", new ParserStub().parseStub("mark"));
        //catches IndexOutOfBoundsException if nothing is keyed in
        assertEquals("You tell me now what task am I supposed to mark if you don't provide me with a number?",
                new ParserStub().parseStub("mark cc"));
    }

    @Test
    public void parseUnmarkTest() {
        assertEquals("unmark 3", new ParserStub().parseStub("unmark 3"));
        //catches NumberFormatException if something other than a number is keyed in
        assertEquals("Brother, key in unmark <space> then a valid number", new ParserStub().parseStub("unmark"));
        //catches IndexOutOfBoundsException if nothing is keyed in
        assertEquals("You tell me now what task am I supposed to unmark if you don't provide me with a number?",
                new ParserStub().parseStub("unmark cc"));
    }

    @Test
    public void parseTodoTest() {
        assertEquals("todo eat", new ParserStub().parseStub("todo eat"));
        //case when Todo name is blank
        assertEquals("Help la, can just tell me what is the name of your task anot?", new ParserStub().parseStub("todo   "));
        //catches IndexOutOfBoundsException if nothing is keyed in
        assertEquals("You trying to test my patience ah? Type \"get commands\" if u blur and dunno how to use me properly.",
                new ParserStub().parseStub("todo"));
    }

    @Test
    public void parseDeadlineTest() {
        assertEquals("deadline homework 03-16-2024 1500", new ParserStub().parseStub("deadline homework / 03-16-2024 1500"));
        //empty input
        assertEquals("Help la, can just tell me what is the name of your task anot?", new ParserStub().parseStub("deadline  "));
        //catches IndexOutOfBoundsException if nothing is keyed in
        assertEquals("You trying to test my patience ah? Check that u got key in the deadline lehhh\n" +
                "Type \"get commands\" if u blur and dunno how to use me properly.",
                new ParserStub().parseStub("deadline "));

    }

    //i more catch indexoob, 1 more blank name

    @Test
    public void parseEventTest() {
        assertEquals("event dinner 03-17-2024 1800 03-17-2024 2000", new ParserStub().parseStub("event dinner / 03-17-2024 1800 / 03-17-2024 2000"));
        //empty input
        assertEquals("Help la, can just tell me what is the name of your task anot?", new ParserStub().parseStub("event dinner / / "));
        //catches IndexOutOfBoundsException if nothing is keyed in
        assertEquals("Eh brother last warning ah. Check that u got key in the start and end time\n" +
                "Type \"get commands\" if u blur and dunno how to use me properly.",
                new ParserStub().parseStub("event sleep"));
    }

    @Test
    public void parseDeleteTest() {
        assertEquals("delete 1", new ParserStub().parseStub("delete 1"));
        //catches NumberFormatException if something other than a number is keyed in
        assertEquals("Brother, key in delete <space> then a valid number", new ParserStub().parseStub("delete"));
        //catches IndexOutOfBoundsException if nothing is keyed in
        assertEquals("You tell me now what task am I supposed to delete if you don't provide me with a number?",
                new ParserStub().parseStub("delete cc"));
    }

    //1more indexoob, 1 more numberformat
}
