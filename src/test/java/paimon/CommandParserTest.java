package paimon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paimon.command.DeadlineCommand;
import paimon.command.EventCommand;
import paimon.command.ExitCommand;
import paimon.command.HelpCommand;
import paimon.command.ListCommand;
import paimon.command.TodoCommand;
import paimon.util.CommandParser;



public class CommandParserTest {

    private CommandParser parser;

    @BeforeEach
    void setUp() {
        // This is optional if you want to initialize anything before each test
    }

    @Test
    void parseInput_byeCommand_success() throws Exception {
        parser = new CommandParser("bye");
        assertInstanceOf(ExitCommand.class, parser.parseInput());
        parser = new CommandParser("bye donuts banana");
        assertInstanceOf(ExitCommand.class, parser.parseInput());
        parser = new CommandParser("bye __=1123+@!#^$^%");
        assertInstanceOf(ExitCommand.class, parser.parseInput());
    }

    @Test
    void parseInput_listCommand_success() throws Exception {
        parser = new CommandParser("list");
        assertInstanceOf(ListCommand.class, parser.parseInput());
        parser = new CommandParser("list donuts banana");
        assertInstanceOf(ListCommand.class, parser.parseInput());
        parser = new CommandParser("list __=1123+@!#^$^%");
        assertInstanceOf(ListCommand.class, parser.parseInput());
    }

    @Test
    void parseInput_helpCommand_success() throws Exception {
        parser = new CommandParser("help");
        assertInstanceOf(HelpCommand.class, parser.parseInput());
        parser = new CommandParser("help donuts banana");
        assertInstanceOf(HelpCommand.class, parser.parseInput());
        parser = new CommandParser("help __=1123+@!#^$^%");
        assertInstanceOf(HelpCommand.class, parser.parseInput());
    }

    @Test
    void parseInput_todoCommand_success() throws Exception {
        parser = new CommandParser("todo buy milk");
        assertInstanceOf(TodoCommand.class, parser.parseInput());
        parser = new CommandParser("todo 101010 0129391 123");
        assertInstanceOf(TodoCommand.class, parser.parseInput());
        parser = new CommandParser("todo !@#+!@# / /1/123/123/");
        assertInstanceOf(TodoCommand.class, parser.parseInput());
    }

    @Test
    void parseInput_todoCommand_chatExceptionThrown() {
        // TodoCommand description should not be empty!
        try {
            parser = new CommandParser("todo ");
            assertInstanceOf(TodoCommand.class, parser.parseInput());
            fail();
        } catch (ChatException e) {
            assertEquals("Input does not match expected format: todo <task>", e.getMessage());
        }

    }

    @Test
    void parseInput_deadlineCommand_success() throws Exception {
        parser = new CommandParser("deadline buy earphones /by 2022-01-20");
        assertInstanceOf(DeadlineCommand.class, parser.parseInput());
        parser = new CommandParser("deadline do projects /by blah blah");
        assertInstanceOf(DeadlineCommand.class, parser.parseInput());
        parser = new CommandParser("deadline stufff /sadas /by something");
        assertInstanceOf(DeadlineCommand.class, parser.parseInput());
    }

    @Test
    void parseInput_deadlineCommand_chatExceptionThrown() {
        parser = new CommandParser("deadline");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("deadline /by this time");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("deadline do stuff");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("deadline do stuff /bwqyqw");
        assertThrows(ChatException.class, () -> parser.parseInput());
    }

    @Test
    void parseInput_eventCommand_success() throws Exception {
        parser = new CommandParser("event work out /from 2022-01-20 /to 2022-01-22");
        assertInstanceOf(EventCommand.class, parser.parseInput());
        parser = new CommandParser("event do stuff /from blah blah /to blah blah");
        assertInstanceOf(EventCommand.class, parser.parseInput());
        parser = new CommandParser("event stuff /sdaf/af /sad/asd /from /asdasd /to /asd/asd");
        assertInstanceOf(EventCommand.class, parser.parseInput());
    }

    @Test
    void parseInput_eventCommand_chatExceptionThrown() {
        parser = new CommandParser("event");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("event work out /from 2022-01-20");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("event work out /to 2022-01-30");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("event /from 2022-01-02 /to 2022-02-03");
        assertThrows(ChatException.class, () -> parser.parseInput());
    }

    @Test
    void testUnknownCommand() {
        parser = new CommandParser("donut ");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("=123=123=/");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("helpp");
        assertThrows(ChatException.class, () -> parser.parseInput());
        parser = new CommandParser("dulete 1");
        assertThrows(ChatException.class, () -> parser.parseInput());
    }

}

