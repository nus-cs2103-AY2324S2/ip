package duke.utils;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void checkSpecialCharacterTest() {
        Parser parser = new Parser();
        // Test for valid input
        String[] input = {"todo", "read", "book"};
        String result = parser.checkSpecialCharacter(input);
        assert result.equals("valid");

        String[] input2 = {"deadline", "read", "book", "/by", "2019-12-01"};
        String result2 = parser.checkSpecialCharacter(input2);
        assert result2.equals("valid");

        String[] input3 = {"event", "read", "book", "and", "eat", "/from", "2019-12-01", "/to", "2019-12-02"};
        String result3 = parser.checkSpecialCharacter(input3);
        assert result3.equals("valid");

        // Test for invalid input
        String[] input4 = {"todo", "read", "book", "|", "2019-12-01"};
        String result4 = parser.checkSpecialCharacter(input4);
        assert result4.equals("Should not use special character |");

        String[] input5 = {"todo", "read", "book", "|2019-12-01"};
        String result5 = parser.checkSpecialCharacter(input5);
        assert result5.equals("Should not use special character |");
    }
}
