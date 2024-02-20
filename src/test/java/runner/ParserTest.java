package runner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void readTest () {
        Parser a = new Parser(new Duke());
        a.handle("dd");
        assertEquals("Not Smart to Understand -_-", outputStreamCaptor.toString().trim());
    }

    @Test
    public void readTest2 () {
        Parser a = new Parser(new Duke());
        a.handle("mark 2");
        assertEquals("Index Out", outputStreamCaptor.toString().trim());
    }

}