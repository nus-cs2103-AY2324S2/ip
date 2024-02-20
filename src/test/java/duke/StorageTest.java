package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class StorageTest {

    @Test
    public void testcase(){
        // normal division results in an integer answer 2
        assertEquals(new Task("Error").getDescription(), new Storage("./data/tasks.txt").convert("hi~there").getDescription());

    }
}