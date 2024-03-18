package bytetalker.parser;

import bytetalker.exception.ByteTalkerException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void test() {
        assertEquals(2, 2);
    }

    @Test
    public void parseDateTime_sampleInput1() {
        assertEquals(LocalDateTime.of(2019, 2, 5, 18, 00),
                Parser.parseDateTime("5/2/2019 1800"));
    }

    @Test
    public void parseDateTime_sampleInput2() {
        assertEquals(LocalDateTime.of(2019, 2, 5, 18, 00),
                Parser.parseDateTime("2019-2-5 1800"));
    }

    @Test
    public void parseDateTime_wrongInput() {
        assertEquals(null,
                        Parser.parseDateTime("5/2/19 1800"));
    }

    @Test
    public void parseDateTime_wrongInput2() {
        assertEquals(null, Parser.parseDateTime("5-2-2019 1800"));
    }

    @Test
    public void parseDateTime_wrongInput3() {
        assertEquals(null, Parser.parseDateTime("5-2-2019 1800"));
    }

    @Test
    public void parseDateTime_noTime() {
        assertEquals(LocalDateTime.of(2019, 2, 5, 23, 59),
                Parser.parseDateTime("2019-2-5"));
    }

    @Test
    public void parseDateTime_wrongInput4() {
        assertEquals(null, Parser.parseDateTime("10-2-5 1800"));
    }

    @Test
    public void parseDateTime_wrongInput5() {
        assertEquals(null, Parser.parseDateTime("test"));
    }

    @Test
    public void parseDateTime_wrongInput6() {
        assertEquals(null, Parser.parseDateTime("2019-5-50"));
    }
}
