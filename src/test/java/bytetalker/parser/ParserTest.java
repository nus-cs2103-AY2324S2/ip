package bytetalker.parser;

import bytetalker.exception.ByteTalkerException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testParseDateTime_sampleInput1() {
        assertEquals(LocalDateTime.of(2019, 2, 5, 18, 00),
                Parser.parseDateTime("5/2/2019 1800"));
    }

    @Test
    public void testParseDateTime_sampleInput2() {
        assertEquals(LocalDateTime.of(2019, 2, 5, 18, 00),
                Parser.parseDateTime("2019-2-5 1800"));
    }

    @Test
    public void testParseDateTime_wrongInput() {
        assertEquals(null,
                        Parser.parseDateTime("5/2/19 1800"));
    }

    @Test
    public void testParseDateTime_wrongInput2() {
        assertEquals(null, Parser.parseDateTime("5-2-2019 1800"));
    }

    @Test
    public void testParseDateTime_wrongInput3() {
        assertEquals(null, Parser.parseDateTime("5-2-2019 1800"));
    }

    @Test
    public void testParseDateTime_noTime() {
        assertEquals(LocalDateTime.of(2019, 2, 5, 23, 59),
                Parser.parseDateTime("2019-2-5"));
    }

    @Test
    public void testParseDateTime_wrongInput4() {
        assertEquals(null, Parser.parseDateTime("10-2-5 1800"));
    }

    @Test
    public void testParseDateTime_wrongInput5() {
        assertEquals(null, Parser.parseDateTime("test"));
    }

    @Test
    public void testParseDateTime_wrongInput6() {
        assertEquals(null, Parser.parseDateTime("2019-5-50"));
    }
}
