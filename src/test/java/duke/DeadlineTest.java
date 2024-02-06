package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests the functionality of the Deadline class */
public class DeadlineTest {
  /** Tests the toString method of the Deadline class */
  @Test
  public void testToString() {
    LocalDateTime deadlineDateTime = LocalDateTime.parse("2022-12-31T18:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    Deadline deadline = new Deadline("Sample Deadline", deadlineDateTime);
    assertEquals("[D][ ] Sample Deadline (by: Dec 31 2022, 6:00PM)", deadline.toString());
  }

  /** Tests the toFileString method of the Deadline class */
  @Test
  public void testToFileString() {
    LocalDateTime deadlineDateTime = LocalDateTime.parse("2022-12-31T18:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    Deadline deadline = new Deadline("Sample Deadline", deadlineDateTime);
    assertEquals("D | 0 | Sample Deadline | 31-12-2022 1800", deadline.toFileString());
  }
}
