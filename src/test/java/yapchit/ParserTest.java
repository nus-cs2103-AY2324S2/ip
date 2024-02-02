package yapchit;

import yapchit.yapchitexceptions.InvalidKeywordException;
import yapchit.yapchitexceptions.YapchitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    private Parser parser;

    public ParserTest() {
        parser = new Parser();
    }

    @Test
    void testParseInputOperationSuccess() throws YapchitException {
        String input = "deadline";
        yapchit.Operations op = yapchit.Operations.DEADLINE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "todo";
        op = yapchit.Operations.TODO;
        assertEquals(op, parser.parseInputOperation(input));

        input = "event";
        op = yapchit.Operations.EVENT;
        assertEquals(op, parser.parseInputOperation(input));

        input = "mark";
        op = yapchit.Operations.MARK;
        assertEquals(op, parser.parseInputOperation(input));

        input = "unmark";
        op = yapchit.Operations.UNMARK;
        assertEquals(op, parser.parseInputOperation(input));

        input = "list";
        op = yapchit.Operations.LIST;
        assertEquals(op, parser.parseInputOperation(input));

        input = "delete";
        op = yapchit.Operations.DELETE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "Delete";
        op = yapchit.Operations.DELETE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "toDo";
        op = yapchit.Operations.TODO;
        assertEquals(op, parser.parseInputOperation(input));

        input = "MARK";
        op = yapchit.Operations.MARK;
        assertEquals(op, parser.parseInputOperation(input));
    }

    @Test
    void testParseInputMissingInput() {
        String input = "";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

    @Test
    void testParseInputWrongKeyword() {
        String input = "blah";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

    @Test
    void testParseInputSpaceInKeyword() {
        String input = "dele te";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

    @Test
    void testParseInputInvalidCharacterAfterKeyword() {
        String input = "mark6";
        assertThrows(InvalidKeywordException.class, () -> {
            parser.parseInputOperation(input);
        });
    }

}


