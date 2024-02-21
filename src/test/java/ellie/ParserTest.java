package ellie;

import static ellie.Parser.UNKNOWN_COMMAND_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ellie.command.ExitCommand;
import ellie.command.InvalidCommand;
import ellie.command.ListCommand;


public class ParserTest {

    //Notation: unitBeingTested_descriptionOfTestInputs_expectedOutcome

    @Test
    public void parse_input_byeCommandReturned() {
        assertEquals(Parser.parse("bye"), new ExitCommand());
    }

    @Test
    public void parse_invalidInput_invalidCommandWithCorrectErrorMessageReturned() {
        assertEquals(Parser.parse("unknowncommand"), new InvalidCommand(UNKNOWN_COMMAND_MESSAGE));
    }

    @Test
    public void parse_listInput_listCommandReturned() {
        assertEquals(Parser.parse("list"), new ListCommand());
    }


}
