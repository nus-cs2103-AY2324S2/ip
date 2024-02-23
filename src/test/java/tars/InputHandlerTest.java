package tars;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest {

    @Test
    public void checkDateConversion() {
        String[] data = {"-", "by 2", "3", "2019"};
        InputHandler ih = new InputHandler();
        LocalDate ld = ih.formatDeadline(data);
        assertEquals(LocalDate.parse("2019-03-02"), ld);

    }
}
