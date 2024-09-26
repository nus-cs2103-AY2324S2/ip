package lai.util;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static lai.util.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_validInput_success() {
        String[] parsed = parse(new Scanner("todo read book"));
        assertEquals(parsed[0], "todo");
        assertEquals(parsed[1], "read book");
    }
}
