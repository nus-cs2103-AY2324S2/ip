package cleo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
public class DeadlineTest {
    @Test
    public void creatingDeadline_ShouldHaveCorrectDescriptionAndDate() throws DukeException {
        Deadline deadline = new Deadline("Submit assignment", "01/12/2021 1800");
        assertEquals("Submit assignment", deadline.getDescription());
        assertEquals(LocalDateTime.of(2021, 12, 1, 18, 0), deadline.getBy()); // Assuming getBy() returns LocalDate
    }

    @Test
    public void toString_ShouldReturnCorrectFormat() throws DukeException {
        Deadline deadline = new Deadline("Submit assignment", "1/12/2021 1800");
        String expectedOutput = "[D][ ] Submit assignment (by: Dec 1 2021 1800)"; // Adjust based on your actual toString format
        assertEquals(expectedOutput, deadline.toString());
    }
}
