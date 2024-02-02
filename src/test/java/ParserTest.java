import kitchensink.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void Test1() {
        assertEquals(2, parser.parse());
    }
}
