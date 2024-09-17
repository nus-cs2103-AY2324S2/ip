package teemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void test1() {
        Parser parser = new Parser();
        List<String> output = parser.parse("todo job1");
        assertEquals(output.size(), 2);
        assertEquals(output.get(0), "todo");
        assertEquals(output.get(1), "job1");
    }

    @Test
    public void test2() {
        Parser parser = new Parser();
        List<String> output = parser.parse("event job2 /from 2090-01-01 /to 2091-01-01");
        assertEquals(output.size(), 4);
        assertEquals(output.get(2), "2090-01-01");
        assertEquals(output.get(3), "2091-01-01");
    }
}
