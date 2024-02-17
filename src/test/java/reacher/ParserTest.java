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
        assertEquals(new ExitCommand(), Parser.parse("bye"));

        assertEquals(new ListCommand(), Parser.parse("list"));

        assertEquals(new EditCommand(), Parser.parse("edit"));

        assertEquals(new AddCommand(), Parser.parse("add"));
    }

    @Test
    public void parse_exceptionThrown(){
        try{
            assertEquals(new AddCommand(), Parser.parse("notcommand"));
            fail();
        } catch (ReacherException e){
            assertEquals("Not a valid command!", e.getMessage());
        }
    }
}
