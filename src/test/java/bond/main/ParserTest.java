/**
 * Tests for the Parser class.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.main;

import bond.command.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parse_invalidCommandType_exceptionThrown() {
        String testUserCommand = "todos brush teeth, wash face, take a shower";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHAT do you MEAN???????????", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDescriptionTodo_exceptionThrown() {
        String testUserCommand = "todo";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("Are you for REAL??? No info for a todo", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDescriptionDeadline_exceptionThrown() {
        String testUserCommand = "deadline";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("Are you for REAL??? No info for a deadline", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDescriptionEvent_exceptionThrown() {
        String testUserCommand = "event";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("Are you for REAL??? No info for a event", e.getMessage());
        }
    }

    @Test
    public void parse_extraDetailsList_exceptionThrown() {
        String testUserCommand = "list abc";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("I see, you are SO EXTRA and saying list needs MORE!!!", e.getMessage());
        }
    }

    @Test
    public void parse_missingIndexMark_exceptionThrown() {
        String testUserCommand = "mark";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHY did you not give me an INDEX to mark a task!!!", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexMark_exceptionThrown() {
        String testUserCommand = "mark abc";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHY did you not give me a PROPER INDEX to mark a task!!!", e.getMessage());
        }
    }

    @Test
    public void parse_missingIndexUnmark_exceptionThrown() {
        String testUserCommand = "unmark";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHY did you not give me an INDEX to unmark a task!!!", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexUnmark_exceptionThrown() {
        String testUserCommand = "unmark abc";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHY did you not give me a PROPER INDEX to unmark a task!!!", e.getMessage());
        }
    }

    @Test
    public void parse_extraDetailsBye_exceptionThrown() {
        String testUserCommand = "bye abc";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("I see, you are SO EXTRA and saying bye needs MORE!!!", e.getMessage());
        }
    }

    @Test
    public void parse_missingIndexDelete_exceptionThrown() {
        String testUserCommand = "delete";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHY did you not give me an INDEX to delete a task!!!", e.getMessage());
        }
    }

    @Test
    public void parse_invalidIndexDelete_exceptionThrown() {
        String testUserCommand = "delete abc";

        try {
            Parser.parse(testUserCommand);
            assert false;
        } catch (BondException e) {
            assertEquals("WHY did you not give me a PROPER INDEX to delete a task!!!", e.getMessage());
        }
    }

    @Test
    public void parse_todo_success() {
        String testUserCommand = "todo sleep";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "todo");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_deadline_success() {
        String testUserCommand = "deadline sleep /by 2024-02-29 12am";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "deadline");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_event_success() {
        String testUserCommand = "event sleep /from 2024-02-29 12am /to 2024-03-01 10am";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "event");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_list_success() {
        String testUserCommand = "list";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "list");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_mark_success() {
        String testUserCommand = "mark 1";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "mark");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_unmark_success() {
        String testUserCommand = "unmark 1";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "unmark");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_bye_success() {
        String testUserCommand = "bye";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "bye");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void parse_delete_success() {
        String testUserCommand = "delete 1";

        try {
            Command c = Parser.parse(testUserCommand);
            assertEquals(c.commandType, "delete");
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}
