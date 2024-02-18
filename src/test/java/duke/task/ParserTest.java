package duke.task;

import duke.parser.Parser;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseListCommandTest(){
        Parser parser = new Parser();

        assertTrue(!parser.parse("list"));
    }

    @Test
    public void parseExitCommandTest(){
        Parser parser = new Parser();

        assertTrue(parser.parse("bye"));
    }

}
