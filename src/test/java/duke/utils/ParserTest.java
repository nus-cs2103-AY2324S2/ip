package duke.utils;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void checkSpecialCharacter_todo_success() {
        Parser parser = new Parser();
        // Test for valid input for todo command.
        String[] input = {"todo", "read", "book"};
        String result = parser.checkSpecialCharacter(input);
        assert result.equals("valid");
    }

    @Test
    public void checkSpecialCharacter_deadline_success() {
        Parser parser = new Parser();
        // Test for valid input for deadline command.
        String[] input2 = {"deadline", "read", "book", "/by", "2019-12-01"};
        String result2 = parser.checkSpecialCharacter(input2);
        assert result2.equals("valid");
    }

    @Test
    public void checkSpecialCharacter_event_success() {
        Parser parser = new Parser();
        // Test for valid input for event command.
        String[] input3 = {"event", "read", "book", "and", "eat", "/from", "2019-12-01", "/to", "2019-12-02"};
        String result3 = parser.checkSpecialCharacter(input3);
        assert result3.equals("valid");
    }

    @Test
    public void checkSpecialCharacter_singleSpecialCharacter_fail() {
        Parser parser = new Parser();
        // Test for invalid input when the special character is one of the input string element.
        String[] input4 = {"todo", "read", "book", "|", "2019-12-01"};
        String result4 = parser.checkSpecialCharacter(input4);
        assert result4.equals("Should not use special character |");
    }

    @Test
    public void checkSpecialCharacter_inPlaceSpecialCharacter_fail() {
        Parser parser = new Parser();
        // Test for invalid input when the special character is in the middle of one input substring.
        String[] input5 = {"todo", "read", "book", "|2019-12-01"};
        String result5 = parser.checkSpecialCharacter(input5);
        assert result5.equals("Should not use special character |");
    }

    @Test
    public void parse_todo_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "todo read book";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.TODO);
    }

    @Test
    public void parse_deadline_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "deadline read book /by 2023-01-01";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.DEADLINE);
    }

    @Test
    public void parse_invalidDeadlineFormat_exceptionThrown() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "deadline read book /to 2023-01-01";
        try {
            parser.parse(input);
        } catch (Exception e) {
            assert e.getMessage().equals("!!!ERROR: Incorrect instruction format. "
              + "The correct format is: \"deadline content /by yyyy-mm-dd\"");
        }
    }

    @Test
    public void parse_event_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "event read book /from 2023-01-01 /to 2023-01-02";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.EVENT);
    }

    @Test
    public void parse_invalidEventFormat_exceptionThrown() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "event read book /from 2023-01-01";
        try {
            parser.parse(input);
        } catch (Exception e) {
            assert e.getMessage().equals("!!!ERROR: Incorrect instruction format. "
              + "The correct format is: \"event content /from yyyy-mm-dd /to yyyy-mm-dd\"");
        }
    }

    @Test
    public void parse_find_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "find read book";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.FIND);
    }

    @Test
    public void parse_findEmptyBody_exceptionThrown() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "find";
        try {
            parser.parse(input);
        } catch (Exception e) {
            assert e.getMessage().equals("!!!ERROR: Please specify the content.");
        }
    }

    @Test
    public void parse_mark_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "mark 1";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.MARK);
    }

    @Test
    public void parse_markInvalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "mark 99";
        try {
            parser.parse(input);
        } catch (Exception e) {
            assert e.getMessage().equals("!!!ERROR: The number is not valid.");
        }
    }

    @Test
    public void parse_unmark_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "unmark 1";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.UNMARK);
    }

    @Test
    public void parse_unmarkInvalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "unmark abc";
        try {
            parser.parse(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert e.getMessage().equals("Error: Please enter a valid number.");
        }
    }

    @Test
    public void parse_delete_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "delete 1";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.DELETE);
    }

    @Test
    public void parse_deleteInvalidIndex_exceptionThrown() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "delete t28";
        try {
            parser.parse(input);
        } catch (Exception e) {
            assert e.getMessage().equals("Error: Please enter a valid number.");
        }
    }

    @Test
    public void parse_list_success() {
        Parser parser = new Parser();
        // Test for invalid input key.
        String input = "list";
        parser.parse(input);
        assert parser.getCurrentKey().equals(KeyEnum.LIST);
    }
}
