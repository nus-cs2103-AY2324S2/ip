package ellie;

import ellie.command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    //Notation: unitBeingTested_descriptionOfTestInputs_expectedOutcome

    @Test
    public void parse_byeInput_ByeCommandReturned() {
        assertEquals( Parser.parse("bye"), new ExitCommand());
    }

    @Test
    public void parse_invalidInput_InvalidCommandWithCorrectErrorMessageReturned() {
        String errorMessage = "Sorry! Not sure what you're referring to (╥_╥) \n" +
                "Type 'help' to view the list of supported commands!\n";
        assertEquals( Parser.parse("unknowncommand"), new InvalidCommand(errorMessage));
    }

    @Test
    public void parse_listInput_ListCommandReturned() {
        assertEquals( Parser.parse("list"), new ListCommand());
    }


}
