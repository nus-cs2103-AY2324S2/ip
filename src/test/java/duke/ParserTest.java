package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidParametersException;

class ParserTest {

    @Test
    public void testGetCommand() throws InvalidInputException {
        assertEquals(duke.UI.Command.BYE, Parser.getCommand(new String[]{"bye"}));
        assertEquals(duke.UI.Command.TODO, Parser.getCommand(new String[]{"todo"}));
        assertEquals(duke.UI.Command.EVENT, Parser.getCommand(new String[]{"event"}));
        assertEquals(UI.Command.DEADLINE, Parser.getCommand(new String[]{"deadline"}));
        assertEquals(UI.Command.LIST, Parser.getCommand(new String[]{"list"}));
        assertThrows(InvalidInputException.class, () -> Parser.getCommand(
              new String[]{"not valid"}),
              "It should throw a InvalidInputException");
        assertEquals(UI.Command.UNMARK, Parser.getCommand(new String[]{"unmark"}));
        assertEquals(UI.Command.MARK, Parser.getCommand(new String[]{"mark"}));
    }

    @Test
    public void testExtractDescription() throws InvalidInputException {
        String[] testArray = new String[]{"event", "do stuff", "/from", "date1", "/to", "date2"};
        assertEquals("do stuff",
            Parser.extractDescriptionData(testArray)[0]);
        assertEquals("date1",
            Parser.extractDescriptionData(testArray)[1]);
        assertEquals("date2",
            Parser.extractDescriptionData(testArray)[2]);
        String[] test_array_2 = new String[]{"event", "do stuff", "date1", "/to", "date2"};
        assertThrows(
            InvalidParametersException.class, () -> Parser.extractDescriptionData(test_array_2),
            "It should throw a InvalidParameterException"
        );
        String[] testArray3 = new String[]{"todo", "do stuff"};
        assertEquals(3, Parser.extractDescriptionData(testArray3).length);
        assertEquals("do stuff",
            Parser.extractDescriptionData(testArray3)[0]);
        assertEquals(null,
            Parser.extractDescriptionData(testArray3)[1]);
        String[] testArray4 = new String[]{"deadline", "do stuff", "/by", "date1"};
        assertEquals("do stuff",
            Parser.extractDescriptionData(testArray4)[0]);
        assertEquals("date1",
            Parser.extractDescriptionData(testArray4)[1]);
        String[] testArray5 = new String[]{"deadline", "do stuff", "date1"};
        assertThrows(
            InvalidParametersException.class, () -> Parser.extractDescriptionData(testArray5),
            "It should throw a InvalidParameterException"
        );
    }
}
