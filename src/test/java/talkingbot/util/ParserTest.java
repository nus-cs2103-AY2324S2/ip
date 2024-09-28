package talkingbot.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import talkingbot.command.ListCommand;

/**
 * Class for testing the Parser class.
 */
public class ParserTest {
    /**
     * Tests the Parser class' parseCommand method to generate
     * a ListCommand.
     */
    @Test
    public void parseListCommandTest() {
        assertEquals(
                new ListCommand(new String[]{"list"})
                        .runCommand(new TaskList(), new SaveFile("data/file.txt"), new Ui()),
                new Parser().parseCommand("list")
                        .runCommand(new TaskList(), new SaveFile("data/file.txt"), new Ui()));
    }
}
