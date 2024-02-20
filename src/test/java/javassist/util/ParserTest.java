package javassist.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import javassist.command.DeadlineCommand;
import javassist.command.DeleteCommand;
import javassist.command.EventCommand;
import javassist.command.FindCommand;
import javassist.command.MarkCommand;
import javassist.command.TodoCommand;
import javassist.exception.JavAssistException;



public class ParserTest {

    @Test
    public void matchPattern_matching_true() {
        assertEquals(true, Parser.matchPattern("mark 1", "mark\\s\\d+"));
    }

    @Test
    public void matchPattern_nonMatching_false() {
        assertEquals(false, Parser.matchPattern("mark 1 1", "mark\\s\\d+"));
    }

    @Test
    public void getCommandType_list_inputTypeList() {
        assertEquals(Parser.InputType.LIST, Parser.getCommandType("list"));
    }

    @Test
    public void getCommandType_listWithSpace_inputTypeUnknown() {
        assertEquals(Parser.InputType.UNKNOWN, Parser.getCommandType("list  "));
    }

    @Test
    public void getCommandType_eventWithSpace_inputTypeEvent() {
        assertEquals(Parser.InputType.EVENT, Parser.getCommandType("event  "));
    }

    @Test
    public void getCommandType_bye_inputTypeUnknown() {
        assertEquals(Parser.InputType.BYE, Parser.getCommandType("bye"));
    }

    @Test
    public void getCommandType_byee_inputTypeUnknown() {
        assertEquals(Parser.InputType.UNKNOWN, Parser.getCommandType("byee"));
    }

    @Test
    public void parseCommand_mark_markCommand() {
        String input = "Mark 1";
        try {
            assertEquals(new MarkCommand(input, true), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_unmark_markCommand() {
        String input = "unmark 1";
        try {
            assertEquals(new MarkCommand(input, false), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_delete_deleteCommand() {
        String input = "delete 1000";
        try {
            assertEquals(new DeleteCommand(input), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_deleted_dukeException() {
        String input = "deleted 1000";
        try {
            Parser.parseCommand(input);
        } catch (JavAssistException e) {
            assertEquals("Your delete instruction is unclear.\nTry 'delete [task number to be deleted]'.",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_todo_todoCommand() {
        String input = "todo read book";
        try {
            assertEquals(new TodoCommand(input), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_todoNoDescription_dukeException() {
        String input = "todo  ";
        try {
            Parser.parseCommand(input);
            fail();
        } catch (JavAssistException e) {
            assertEquals("The description of a todo cannot be empty.\nTry 'todo [task description]'.",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadline_deadlineCommand() {
        String input = "deadline return book /by 20-12-2023 17:10";
        try {
            assertEquals(new DeadlineCommand(input), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }
    @Test
    public void parseCommand_deadlineMissingDate_dukeException() {
        String input = "deadline return book /by ";
        try {
            Parser.parseCommand(input);
            fail();
        } catch (JavAssistException e) {
            assertEquals("The description and due of a deadline cannot be empty.\n"
                    + "Try 'deadline [task description] /by [dd-MM-yyyy HH:mm]'.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineMissingDescription_dukeException() {
        String input = "deadline /by 20-12-2023 17:10";
        try {
            Parser.parseCommand(input);
            fail();
        } catch (JavAssistException e) {
            assertEquals("The description and due of a deadline cannot be empty.\n"
                    + "Try 'deadline [task description] /by [dd-MM-yyyy HH:mm]'.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_event_eventCommand() {
        String input = "event meeting /from 20-12-2023 17:10 /to 20-12-2023 17:30";
        try {
            assertEquals(new EventCommand(input), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_eventMissingStart_dukeException() {
        String input = "event meeting /to 20-12-2023 17:10";
        try {
            Parser.parseCommand(input);
            fail();
        } catch (JavAssistException e) {
            assertEquals("The description, start and end time of an event cannot be empty.\n"
                    + "Try 'event [task description] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]'.",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventSwapStartEnd_dukeException() {
        String input = "event meeting /to 20-12-2023 17:10 /from 20-12-2023 10:00";
        try {
            Parser.parseCommand(input);
            fail();
        } catch (JavAssistException e) {
            assertEquals("The description, start and end time of an event cannot be empty.\n"
                    + "Try 'event [task description] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]'.",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_find_findCommand() {
        String input = "find meeting";
        try {
            assertEquals(new FindCommand(input), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_findMultipleKeywords_findCommand() {
        String input = "find meeting read do";
        try {
            assertEquals(new FindCommand("meeting", "read", "do"), Parser.parseCommand(input));
        } catch (JavAssistException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_findNoKeyword_dukeException() {
        String input = "find ";
        try {
            Parser.parseCommand(input);
            fail();
        } catch (JavAssistException e) {
            assertEquals("Specify 1 or more keyword/s to search.\nTry 'find [keywords]'.", e.getMessage());
        }
    }
}
