package lumiere.lumiere; //same package as the class being tested

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.time.LocalDate;

public class LumiereTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void dummyTest() {
        try {
            new TaskList().addTask("deadline testing /by NEVER!", new Storage("./lumiere.txt"));
        } catch (IOException err) {
            System.out.println("SOMETHING IS WRONG");
        }

        assertEquals("Sorry! Your deadline is in the wrong format. Correct format is YYYY-MM-DD",
                outContent.toString().trim());
    }

    @Test
    public void anotherDummyTest() {
        String time = "2024-01-12"; // represents 12 Jan 2024, NOT 1 Dec 2024!!

        Deadline test = new Deadline("Visit National Museum", true, LocalDate.parse(time), time);

        assertEquals("[D][X] Visit National Museum (by: Jan 12 2024)",
                test.stringify());
    }

    public void dummyJavaTest() {
        try {
            new TaskList().addTask("deadline testing /by NEVER!", new Storage("./lumiere.txt"));
        } catch (IOException err) {
            System.out.println("SOMETHING IS WRONG");
        }

        assert "Sorry! Your deadline is in the wrong format. Correct format is YYYY-MM-DD" == outContent.toString()
                .trim();
    }

    public void anotherDummyJavaTest() {
        String time = "2024-01-12"; // represents 12 Jan 2024, NOT 1 Dec 2024!!

        Deadline test = new Deadline("Visit National Museum", true, LocalDate.parse(time), time);

        assert "[D][X] Visit National Museum (by: Jan 12 2024)" == test.stringify();
    }
}