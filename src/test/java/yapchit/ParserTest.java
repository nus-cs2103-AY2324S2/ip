package yapchit;

import yapchit.yapchitbackend.YapchitBackend;
import yapchit.yapchitbackend.Parser;
import yapchit.yapchitexceptions.InvalidKeywordException;
import yapchit.yapchitexceptions.YapchitException;
import org.junit.jupiter.api.Test;
import yapchit.yapchitui.Yapchit;

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


