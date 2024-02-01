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
        Yapchit.Operations op = Yapchit.Operations.DEADLINE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "todo";
        op = Yapchit.Operations.TODO;
        assertEquals(op, parser.parseInputOperation(input));

        input = "event";
        op = Yapchit.Operations.EVENT;
        assertEquals(op, parser.parseInputOperation(input));

        input = "mark";
        op = Yapchit.Operations.MARK;
        assertEquals(op, parser.parseInputOperation(input));

        input = "unmark";
        op = Yapchit.Operations.UNMARK;
        assertEquals(op, parser.parseInputOperation(input));

        input = "list";
        op = Yapchit.Operations.LIST;
        assertEquals(op, parser.parseInputOperation(input));

        input = "delete";
        op = Yapchit.Operations.DELETE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "Delete";
        op = Yapchit.Operations.DELETE;
        assertEquals(op, parser.parseInputOperation(input));

        input = "toDo";
        op = Yapchit.Operations.TODO;
        assertEquals(op, parser.parseInputOperation(input));

        input = "MARK";
        op = Yapchit.Operations.MARK;
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


