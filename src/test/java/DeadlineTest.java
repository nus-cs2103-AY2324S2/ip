import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.Tag;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2024, 2, 13);
        Deadline deadline = new Deadline("Submit report", date);
        assertEquals("[D][ ] Submit report (by: 2024-02-13)", deadline.toString(),
                "toString does not match expected output without tag.");

        deadline.addTag("Urgent");
        assertEquals("[D][ ] Submit report (by: 2024-02-13) Urgent", deadline.toString(),
                "toString does not match expected output with tag.");
    }

    @Test
    void testToFileString() {
        LocalDate date = LocalDate.of(2024, 2, 13);
        Deadline deadline = new Deadline("Complete assignment", date);
        assertEquals("D | 0 | Complete assignment | 2024-02-13", deadline.toFileString(),
                "toFileString does not match expected output without tag.");

        deadline.markAsDone();
        assertEquals("D | 1 | Complete assignment | 2024-02-13", deadline.toFileString(),
                "toFileString does not match expected output for done task without tag.");

        deadline.addTag("HighPriority");
        assertEquals("D | 1 | Complete assignment | 2024-02-13 | HighPriority", deadline.toFileString(),
                "toFileString does not match expected output for done task with tag.");
    }

    @Test
    void testFromFileString() {
        String fileString = "D | 0 | Finish project | 2024-02-20";
        Deadline deadline = Deadline.fromFileString(fileString);
        assertNotNull(deadline, "Deadline should not be null.");
        assertEquals("Finish project", deadline.getDescription(), "Description does not match.");
        assertEquals("2024-02-20", deadline.getDate(), "Date does not match.");
        assertFalse(deadline.isDone(), "Task should not be marked as done.");

        String fileStringWithTag = "D | 1 | Review code | 2024-03-01 | CodeReview";
        Deadline deadlineWithTag = Deadline.fromFileString(fileStringWithTag);
        assertNotNull(deadlineWithTag, "Deadline with tag should not be null.");
        assertTrue(deadlineWithTag.isDone(), "Task should be marked as done.");
        Tag tag = deadlineWithTag.getTag();
        assertEquals("CodeReview", tag.getTagName(), "Tag does not match.");
    }

    @Test
    void testAddAndRemoveTag() {
        LocalDate date = LocalDate.of(2024, 2, 13);
        Deadline deadline = new Deadline("Update documentation", date);
        Tag tag = deadline.getTag();
        assertNull(tag, "Initially, tag should be null.");

        deadline.addTag("Important");
        Tag tag2 = deadline.getTag();
        assertNotNull(tag2, "Tag should not be null after adding.");
        assertEquals("Important", tag2.getTagName(), "Tag name does not match after adding.");

        deadline.removeTag();
        Tag tag3 = deadline.getTag();
        assertNull(tag3, "Tag should be null after removal.");
    }
}

