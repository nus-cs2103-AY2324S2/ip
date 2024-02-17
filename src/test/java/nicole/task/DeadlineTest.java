package nicole.task;

import nicole.nicoleexceptions.NicoleException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadline_noDescriptionGiven_nicoleExceptionThrown() {
        try {
            new Deadline("2024 by null");
            fail();
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Describe your deadline like this: deadline [task] by YYYY-MM-DD",
                    e.toString());
        }
    }
    @Test
    public void deadline_noDeadlineGiven_nicoleExceptionThrown() {
        try {
            new Deadline("return book by null");
            fail();
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Describe your deadline like this: deadline [task] by YYYY-MM-DD",
                    e.toString());
        }
    }

    @Test
    public void parseDate_invalidLocalDate_nicoleExceptionThrown() {
        try {
            new Deadline("return book by 2024-30-49");
            fail();
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Are you sure your date is in the proper [YYYY-MM-DD] format...?",
                    e.toString());
        }
    }

    @Test
    public void parseDate_impossibleDate_nicoleExceptionThrown() {
        try {
            new Deadline("return book by 2010-01-01");
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Erm, the deadline can't be before now right...",
                    e.toString());
        }
    }

    @Test
    void deadlineCreation_perfectConditions_toStringCorrect() {
        try {
            Deadline testDeadline = new Deadline("return book by 2024-12-31");
            assertEquals("[D][I] return book by 2024-12-31", testDeadline.toString());
        } catch (NicoleException e) {
            fail();
        }
    }
}
