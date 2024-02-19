package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {
    @Test
    public void Event_Test_Success(){
        assertEquals(new Event(" this is a test", "2019-10-15" , "2019-10-16").ToString(),"[E] [ ]  this is a test (from: Oct 15 2019 to: Oct 16 2019) (none)" );
        assertEquals(new Event(" this is a test", "2019-10-15" , "2019-10-16").toStore(),"E/0/ this is a test/NONE/2019-10-15/2019-10-16" );
    }
}
