package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;




public class ParserTest {

    @Test
    public void parseEventArgTest() {
        assertDoesNotThrow(() -> {
            String[] testArray = Parser.parseEventArgument("Description /from 2019-12-02 /to 2019-12-03");
            assertEquals(testArray[0], "Description ");
            assertEquals(testArray[1], " 2019-12-02 ");
            assertEquals(testArray[2], " 2019-12-03");
        });
    }
}
