package duke;

import duke.command.*;
import duke.exception.CommandInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    String dummyCommandA = "todo return book";
    String dummyCommandB = "list";
    String dummyCommandC = "mark 1";
    String dummyCommandD = "unmark 1";
    String dummyCommandE = "delete 1";
    String dummyCommandF = "bye";
    String dummyCommandG = "bwah";


    /**
     * Tests Parser to be able to parse
     * every available commands.
     *
     */
    @Test
    public void parse_success() throws CommandInvalidException {
        AddCommand expectedA = new AddCommand(dummyCommandA);
        AddCommand cA = (AddCommand) Parser.parse(dummyCommandA);

        assertEquals(expectedA.getCom(),cA.getCom());

        Command expectedB = new ListCommand();
        Command cB = Parser.parse(dummyCommandB);

        assertEquals(expectedB.isExit(),cB.isExit());

        MarkCommand expectedC = new MarkCommand(dummyCommandC);
        MarkCommand cC = (MarkCommand) Parser.parse(dummyCommandC);

        assertEquals(expectedC.getCom(),cC.getCom());

        UnmarkCommand expectedD = new UnmarkCommand(dummyCommandD);
        UnmarkCommand cD = (UnmarkCommand) Parser.parse(dummyCommandD);

        assertEquals(expectedD.getCom(),cD.getCom());

        DeleteCommand expectedE = new DeleteCommand(dummyCommandE);
        DeleteCommand cE = (DeleteCommand) Parser.parse(dummyCommandE);

        assertEquals(expectedE.getCom(),cE.getCom());

        Command expectedF = new ByeCommand();
        Command cF = Parser.parse(dummyCommandF);

        assertEquals(expectedF.isExit(),cF.isExit());

    }

    /**
     * Tests whether the exception for the
     * parser is thrown when given invalid command.
     *
     */
    @Test
    public void parse_exception(){
        try{
            assertEquals(new ListCommand(), Parser.parse(dummyCommandG));
            fail();
        } catch (CommandInvalidException e){
            assertEquals("Invalid command -_-, please use the available commands!!",e.getMessage());
        }
    }


}
