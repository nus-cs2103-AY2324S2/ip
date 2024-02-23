package yapchit;

import org.junit.jupiter.api.Test;
import yapchit.yapchitbackend.Parser;
import yapchit.yapchitbackend.YapchitBackend;
import yapchit.yapchitexceptions.InvalidKeywordException;
import yapchit.yapchitexceptions.YapchitException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test parser class.
 */
public class ParserTest {

    private Parser parser;

    /**
     * Creates new parser test instance.
     */
    public ParserTest() {
        parser = new Parser();
    }

    /**
     * Tests success condition of parseInputOperation method of the parser class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testParseInputOperationSuccess() throws YapchitException {
        String input = "deadline";
        YapchitBackend.Operations op = YapchitBackend.Operations.DEADLINE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "todo";
        op = YapchitBackend.Operations.TODO;
        assertEquals(op, parser.parseInputOperation(input));

        input = "event";
        op = YapchitBackend.Operations.EVENT;
        assertEquals(op, parser.parseInputOperation(input));

        input = "mark";
        op = YapchitBackend.Operations.MARK;
        assertEquals(op, parser.parseInputOperation(input));

        input = "unmark";
        op = YapchitBackend.Operations.UNMARK;
        assertEquals(op, parser.parseInputOperation(input));

        input = "list";
        op = YapchitBackend.Operations.LIST;
        assertEquals(op, parser.parseInputOperation(input));

        input = "delete";
        op = YapchitBackend.Operations.DELETE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "Delete";
        op = YapchitBackend.Operations.DELETE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "toDo";
        op = YapchitBackend.Operations.TODO;
        assertEquals(op, parser.parseInputOperation(input));

        input = "MARK";
        op = YapchitBackend.Operations.MARK;
        assertEquals(op, parser.parseInputOperation(input));
    }

    /**
     * Tests missing input fail condition of parseInputOperation method of the parser class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testParseInputMissingInput() {
        String input = "";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

    /**
     * Tests wrong keyword fail condition of parseInputOperation method of the parser class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testParseInputWrongKeyword() {
        String input = "blah";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

    /**
     * Tests space in keyword fail condition of parseInputOperation method of the parser class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testParseInputSpaceInKeyword() {
        String input = "dele te";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

    /**
     * Tests invalid character after keyword fail condition of parseInputOperation method of the parser class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testParseInputInvalidCharacterAfterKeyword() {
        String input = "mark6";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

}


