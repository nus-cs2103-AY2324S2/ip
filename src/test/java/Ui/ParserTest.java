package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.KewgyException;
import tasks.Task;
import ui.Parser;

public class ParserTest {

    private Parser parser;
    private List<Task> taskList;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        taskList = new ArrayList<>();
        
        try {
            taskList.add(new Task("todo Task 1"));
            taskList.add(new Task("todo Task 2"));
        } catch (KewgyException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testParseUserMsgValidCommands() {
        assertEquals(Parser.Command.BYE, parser.parseUserMsg(new String[]{"bye"}));
        assertEquals(Parser.Command.LIST, parser.parseUserMsg(new String[]{"list"}));
        assertEquals(Parser.Command.MARK, parser.parseUserMsg(new String[]{"mark"}));
        assertEquals(Parser.Command.UNMARK, parser.parseUserMsg(new String[]{"unmark"}));
        assertEquals(Parser.Command.TODO, parser.parseUserMsg(new String[]{"todo"}));
        assertEquals(Parser.Command.DEADLINE, parser.parseUserMsg(new String[]{"deadline"}));
        assertEquals(Parser.Command.EVENT, parser.parseUserMsg(new String[]{"event"}));
        assertEquals(Parser.Command.DELETE, parser.parseUserMsg(new String[]{"delete"}));
        assertEquals(Parser.Command.FIND, parser.parseUserMsg(new String[]{"find"}));
        assertEquals(Parser.Command.UPDATE_TIME, parser.parseUserMsg(new String[]{"update_time"}));
        assertEquals(Parser.Command.UNKNOWN, parser.parseUserMsg(new String[]{"random"}));
    }

    @Test
    public void testCheckValidMarkCommandWithInsufficientArguments() {
        assertFalse(parser.checkValidMarkCommand(new String[]{"mark"}, taskList));
    }

    @Test
    public void testCheckValidMarkCommandWithInvalidIndex() {
        assertFalse(parser.checkValidMarkCommand(new String[]{"mark", "a"}, taskList));
    }

    @Test
    public void testCheckValidMarkCommandWithIndexOutOfRange() {
        assertFalse(parser.checkValidMarkCommand(new String[]{"mark", "0"}, taskList));
    }
}
