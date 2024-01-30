import org.junit.jupiter.api.Test;
import solaire.data.exception.SolaireException;
import solaire.data.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void testDeadlineParsing() {
        // test case 1
        try {
            String testDdlString = "2024-02-27";
            Deadline ddl = new Deadline("ddl", testDdlString);
        } catch (SolaireException e) {
            fail("Failed test case 1 in deadline test");
        }

        // test case 2
        String testInput2 = "deadline Complete level 7 /by tomorrow";
        try {
            String testDdlString = "tomorrow";
            Deadline ddl = new Deadline("ddl", testDdlString);
            fail("Failed test case 2 in deadline test");
        } catch (SolaireException e) {
            assertEquals("Deadline must be in the format: yyyy-mm-dd", e.getMessage());
        }

    }

}
