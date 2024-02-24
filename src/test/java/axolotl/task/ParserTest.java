package axolotl.task;

import axolotl.command.bye.ByeCommand;
import axolotl.command.list.ListCommand;
import axolotl.parser.Parser;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    /**
     * Checks if list command works
     */
    @Test
    public void parseListCommandTest(){
        Parser parser = new Parser();

        assertTrue(Objects.equals(parser.parse("list"), new ListCommand()));
    }

    /**
     * Checks if exit command works
     */
    @Test
    public void parseExitCommandTest(){
        Parser parser = new Parser();

        assertTrue(Objects.equals(parser.parse("bye"), new ByeCommand()));
    }

}
