package duke.task;

import duke.parser.Parser;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseListCommandTest(){
        Parser parser = new Parser();
        Ui ui = new Ui();

        assertTrue(!parser.parse("list", ui));
    }

    @Test
    public void parseExitCommandTest(){
        Parser parser = new Parser();
        Ui ui = new Ui();

        assertTrue(parser.parse("bye", ui));
    }

}
