package reacher;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    @Test
    public void readString_success() throws ReacherException {
        String input = "input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(input, new Ui().readString());
    }

    @Test
    public void readString_emptyException(){
        String input = "/n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            assertEquals(input, new Ui().readString());
        } catch (ReacherException e) {
            assertEquals("pls type a task name.", e.getMessage());
        }
    }
}
