package ezra;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Parser} class.
 */
public class ParserTest {

    /**
     * Test cases for the {@link Parser#parseToDo(String)} method.
     *
     * @throws WrongFormatException if the input has an unexpected format
     */
    @Test
    public void testParseToDo() throws WrongFormatException {
        assertEquals(new ToDo("return books"), Parser.parseToDo("todo return books"));

        // Missing description
        assertThrows(WrongFormatException.class, () -> Parser.parseToDo("todo"));
        assertThrows(WrongFormatException.class, () -> Parser.parseToDo("todo "));
    }

    /**
     * Test cases for the {@link Parser#extractToDoDescription(String)} method.
     */
    @Test
    public void testExtractToDoDescription() {
        assertEquals("return books", Parser.extractToDoDescription("todo return books"));
        assertEquals("buy groceries", Parser.extractToDoDescription("todo buy groceries"));
        assertEquals("submit CS2103T ip", Parser.extractToDoDescription("todo submit CS2103T ip"));
    }

    /**
     * Test cases for the {@link Parser#parseDeadline(String)} method.
     *
     * @throws WrongFormatException if the input has an unexpected format
     */
    @Test
    public void testParseDeadline() throws WrongFormatException {
        assertEquals(new Deadline("return books", "29/01/2024 1800"),
                Parser.parseDeadline("deadline return books /by 29/01/2024 1800"));

        // Missing description
        assertThrows(WrongFormatException.class, () -> Parser.parseDeadline("deadline /by 29/01/24 1800"));
        // Missing deadline
        assertThrows(WrongFormatException.class, () -> Parser.parseDeadline("deadline return books"));
        assertThrows(WrongFormatException.class, () -> Parser.parseDeadline("deadline return books /by"));
        // No "/" before by
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDeadline("deadline return books by 29/01/24 1800"));
    }

    /**
     * Test cases for the {@link Parser#extractDeadlineDescription(String)} method.
     */
    @Test
    public void testExtractDeadlineDescription() {
        assertEquals("return books",
                Parser.extractDeadlineDescription("deadline return books /by 29/01/2024 1800"));
        assertEquals("submit assignment",
                Parser.extractDeadlineDescription("deadline submit assignment /by 16/02/2024 1200"));
    }

    /**
     * Test cases for the {@link Parser#extractDeadlineBy(String)} method.
     */
    @Test
    public void testExtractDeadlineBy() {
        assertEquals("29/01/2024 1800",
                Parser.extractDeadlineBy("deadline return books /by 29/01/2024 1800"));
        assertEquals("16/02/2024 1200",
                Parser.extractDeadlineBy("deadline submit assignment /by 16/02/2024 1200"));
    }

    /**
     * Test cases for the {@link Parser#parseEvent(String)} method
     *
     * @throws WrongFormatException if the input has an unexpected format
     */
    @Test
    public void testParseEvent() throws WrongFormatException {
        assertEquals(new Event("CS2103T briefing", "16/02/2024 1600", "16/02/2024 1700"),
                Parser.parseEvent("event CS2103T briefing /from 16/02/2024 1600 /to 16/02/2024 1700"));

        // Missing description
        assertThrows(WrongFormatException.class, () ->
                Parser.parseEvent("event /from 16/02/2024 1600 /to 16/02/2024 1700"));
        // Missing to
        assertThrows(WrongFormatException.class, () ->
                Parser.parseEvent("event CS2103T briefing /from 16/02/2024 1600 /to"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseEvent("event CS2103T briefing /from 16/02/2024 1600"));
        // Missing from
        assertThrows(WrongFormatException.class, () ->
                Parser.parseEvent("event CS2103T briefing /to 16/02/2024 1700"));
    }

    /**
     * Test cases for the {@link Parser#extractEventDescription(String)} method
     */
    @Test
    public void testExtractEventDescription() {
        assertEquals("CS2103T briefing",
                Parser.extractEventDescription(
                        "event CS2103T briefing /from 16/02/2024 1600 /to 16/02/2024 1700"));
        assertEquals("project meeting",
                Parser.extractEventDescription(
                        "event project meeting /from 18/02/2024 2100 /to 18/02/2024 2200"));
    }

    /**
     * Test cases for the {@link Parser#extractEventFrom(String)} method
     */
    @Test
    public void testExtractEventFrom() {
        assertEquals("16/02/2024 1600",
                Parser.extractEventFrom("event CS2103T briefing /from 16/02/2024 1600 /to 16/02/2024 1700"));
        assertEquals("18/02/2024 2100",
                Parser.extractEventFrom("event project meeting /from 18/02/2024 2100 /to 18/02/2024 2200"));
    }

    /**
     * Test cases for the {@link Parser#extractEventTo(String)} method
     */
    @Test
    public void testExtractEventTo() {
        assertEquals("16/02/2024 1700",
                Parser.extractEventTo("event CS2103T briefing /from 16/02/2024 1600 /to 16/02/2024 1700"));
        assertEquals("18/02/2024 2200",
                Parser.extractEventTo("event project meeting /from 18/02/2024 2100 /to 18/02/2024 2200"));
    }

    /**
     * Test cases for the {@link Parser#parseDelete(String)} method.
     *
     * @throws WrongFormatException if the input has an unexpected format
     */
    @Test
    public void testParseDelete() throws WrongFormatException {
        assertThrows(WrongFormatException.class, () -> Parser.parseDelete("delete -1"));
        assertThrows(WrongFormatException.class, () -> Parser.parseDelete("delete 1 2 3 -1"));
        assertThrows(WrongFormatException.class, () -> Parser.parseDelete("delete abc"));
        assertThrows(WrongFormatException.class, () -> Parser.parseDelete("delete 12a"));
        assertThrows(WrongFormatException.class, () -> Parser.parseDelete("delete"));
        assertThrows(WrongFormatException.class, () -> Parser.parseDelete("delete "));
    }

    /**
     * Test cases for the {@link Parser#parseMark(String)} method.
     *
     * @throws WrongFormatException if the input has an unexpected format
     */
    @Test
    public void testParseMark() throws WrongFormatException {
        assertThrows(WrongFormatException.class, () -> Parser.parseMark("mark -1"));
        assertThrows(WrongFormatException.class, () -> Parser.parseMark("mark 1 2 3 -1"));
        assertThrows(WrongFormatException.class, () -> Parser.parseMark("mark abc"));
        assertThrows(WrongFormatException.class, () -> Parser.parseMark("mark 12a"));
        assertThrows(WrongFormatException.class, () -> Parser.parseMark("mark"));
        assertThrows(WrongFormatException.class, () -> Parser.parseMark("mark "));
    }

    /**
     * Test cases for the {@link Parser#parseUnmark(String)} method.
     *
     * @throws WrongFormatException if the input has an unexpected format
     */
    @Test
    public void testParseUnmark() throws WrongFormatException {
        assertThrows(WrongFormatException.class, () -> Parser.parseUnmark("unmark -1"));
        assertThrows(WrongFormatException.class, () -> Parser.parseUnmark("unmark 1 2 3 -1"));
        assertThrows(WrongFormatException.class, () -> Parser.parseUnmark("unmark abc"));
        assertThrows(WrongFormatException.class, () -> Parser.parseUnmark("unmark 12a"));
        assertThrows(WrongFormatException.class, () -> Parser.parseUnmark("unmark"));
        assertThrows(WrongFormatException.class, () -> Parser.parseUnmark("unmark "));
    }

    /**
     * Test cases for the {@link Parser#extractTaskIndices(String)} method.
     */
    @Test
    public void testExtractTaskIndices() {
        assertArrayEquals(new int[]{0}, Parser.extractTaskIndices("delete 1"));
        assertArrayEquals(new int[]{9}, Parser.extractTaskIndices("delete 10"));
        assertArrayEquals(new int[]{0, 1, 2}, Parser.extractTaskIndices("delete 1 2 3"));
        assertArrayEquals(new int[]{9, 10, 11, 12}, Parser.extractTaskIndices("delete 10 11 12 13"));

        assertArrayEquals(new int[]{0}, Parser.extractTaskIndices("mark 1"));
        assertArrayEquals(new int[]{9}, Parser.extractTaskIndices("mark 10"));
        assertArrayEquals(new int[]{0, 1, 2}, Parser.extractTaskIndices("mark 1 2 3"));
        assertArrayEquals(new int[]{9, 10, 11, 12}, Parser.extractTaskIndices("mark 10 11 12 13"));

        assertArrayEquals(new int[]{0}, Parser.extractTaskIndices("unmark 1"));
        assertArrayEquals(new int[]{9}, Parser.extractTaskIndices("unmark 10"));
        assertArrayEquals(new int[]{0, 1, 2}, Parser.extractTaskIndices("unmark 1 2 3"));
        assertArrayEquals(new int[]{9, 10, 11, 12}, Parser.extractTaskIndices("unmark 10 11 12 13"));
    }

    /**
     * Test cases for the {@link Parser#parseFind(String)} method.
     */
    @Test
    public void testParseFind() throws WrongFormatException {
        assertThrows(WrongFormatException.class, () -> Parser.parseFind("find "));
        assertThrows(WrongFormatException.class, () -> Parser.parseFind("find"));
    }

    /**
     * Test cases for the {@link Parser#extractKeyword(String)} method.
     */
    @Test
    public void extractKeyword() {
        assertEquals("cs2103t", Parser.extractKeyword("find cs2103t"));
        assertEquals("return books", Parser.extractKeyword("find return books"));
    }
}
