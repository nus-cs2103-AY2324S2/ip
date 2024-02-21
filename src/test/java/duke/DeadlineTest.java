package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest(){
        assertEquals(new Deadline("return book", "2021-08-25T18:00").toString(),
            "[D][ ] return book (by: Aug 25 2021 18:00:00)");
    }
}
