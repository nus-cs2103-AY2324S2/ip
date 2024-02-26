package reacher;

import org.junit.jupiter.api.Test;
import reacher.command.AddCommand;
import reacher.command.EditCommand;
import reacher.command.ExitCommand;
import reacher.command.ListCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {

    @Test
    public void parse_success() throws ReacherException {
        assertEquals(new ExitCommand(), Parser.getCommand("bye"));

        assertEquals(new ListCommand(), Parser.getCommand("list"));

        assertEquals(new EditCommand(), Parser.getCommand("edit"));

        assertEquals(new AddCommand(), Parser.getCommand("add"));
    }

    @Test
    public void parse_exceptionThrown(){
        try{
            assertEquals(new AddCommand(), Parser.getCommand("notcommand"));
            fail();
        } catch (ReacherException e){
            assertEquals("Not a valid command!", e.getMessage());
        }
    }
}
