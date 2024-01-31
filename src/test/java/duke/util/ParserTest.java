package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {
    @Test
    public void splitCommand_success() {
        String[] expected = {"event", "Open House 2024 ", "2022-10-10 ", "2022-10-11 "};
        String [] result = Parser.splitCommand("event Open House 2024 /from 2022-10-10 /to 2022-10-11 ");
        assertArrayEquals(expected, result);
    }
}
