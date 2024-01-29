package campus.tasks;

import campus.exceptions.CampusException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    /**
     * Simple testing of the formatter, will throw an error if the string parameters are not in the correct format
     */
    @Test
    public void incorrectDTF(){
        assertThrows(CampusException.class,() -> new Event("Dinner", "7pm 29 Jan 2024", "10pm 29 Jan 2024"));
    }

    /**
     * Simple testing of the formatter, will throw an error if the string parameters are missing or incomplete or empty
     */
    @Test
    public void missingParameters(){
        assertThrows(CampusException.class,() -> new Event("Dinner", "", ""));
    }
}
