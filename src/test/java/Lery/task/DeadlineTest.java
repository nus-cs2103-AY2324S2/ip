package Lery.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testGetType(){
        assertEquals("D", new Deadline("return book", "2024/01/22").getType());
    }

    @Test
    public void testGetExtraInfo(){
        assertEquals("(by: Jan 22 2024)", new Deadline("return book", "2024/01/22").getExtraInfo());
    }
    @Test
    public void testGetExtraInfoShortened(){
        assertEquals("2024-01-22", new Deadline("return book", "2024/01/22").getExtraInfoShortened());
    }
}
