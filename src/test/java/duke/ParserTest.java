package duke;
import duke.Parser;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidParametersException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

  @Test
  public void testGetCommand() throws InvalidInputException {
    assertEquals(duke.UI.Command.BYE, Parser.getCommand(new String[]{"bye"}));
    assertEquals(UI.Command.LIST, Parser.getCommand(new String[]{"list"}));
    assertThrows(InvalidInputException.class, () -> Parser.getCommand(new String[]{"not valid"}),
      "It should throw a InvalidInputException");
  }

  @Test
  public void testExtractDescription() throws InvalidInputException {
    String[] test_array = new String[]{"event", "do stuff", "/from", "date1", "/to", "date2"};
    assertEquals("do stuff",
      Parser.extractDescriptionData(test_array)[0]);
    assertEquals("date1",
      Parser.extractDescriptionData(test_array)[1]);
    assertEquals("date2",
      Parser.extractDescriptionData(test_array)[2]);
    String[] test_array_2 = new String[]{"event", "do stuff", "date1", "/to", "date2"};
    assertThrows(
      InvalidParametersException.class, () -> Parser.extractDescriptionData(test_array_2),
      "It should throw a InvalidParameterException"
    );
    String[] test_array_3 = new String[]{"todo", "do stuff"};
    assertEquals(3, Parser.extractDescriptionData(test_array_3).length);
    assertEquals("do stuff",
      Parser.extractDescriptionData(test_array_3)[0]);
    assertEquals(null,
      Parser.extractDescriptionData(test_array_3)[1]);
  }

}