package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testToDoCreation() {
        String description = "Read book";
        ToDo todo = new ToDo(description);
        assertNotNull(todo);
        assertEquals(description, todo.getDescription());
        assertEquals("[T] [ ] " + description, todo.toString(), "ToDo.toString() should return the correct format.");
    }

    @Test
    public void testToDoComparison() {
        String description1 = "Read book";
        ToDo todo1 = new ToDo(description1);

        String description2 = "Eat cake";
        ToDo todo2 = new ToDo(description2);

        String description3 = "Eat cake";
        ToDo todo3 = new ToDo(description3);

        assertTrue(todo1.compareTo(todo2) != 0);
        assertTrue(todo2.compareTo(todo3) == 0);
    }

    @Test
    void testEventCreation() {
        LocalDate start = LocalDate.of(2024, 2, 25);
        LocalDate end = LocalDate.of(2024, 2, 26);
        Event event = new Event("Meeting", start, end);

        assertNotNull(event);
        assertEquals("Meeting", event.getDescription());
        assertEquals("[E] [ ] Meeting (from: Feb 25 2024 to: Feb 26 2024)", event.toString());
    }

    @Test
    void testEventComparison() {
        LocalDate start1 = LocalDate.of(2024, 2, 25);
        LocalDate end1 = LocalDate.of(2024, 2, 26);
        Event event1 = new Event("Meeting", start1, end1);

        LocalDate start2 = LocalDate.of(2024, 2, 27);
        LocalDate end2 = LocalDate.of(2024, 2, 28);
        Event event2 = new Event("Conference", start2, end2);

        LocalDate start3 = LocalDate.of(2024, 2, 27);
        LocalDate end3 = LocalDate.of(2024, 2, 28);
        Event event3 = new Event("Conference", start3, end3);

        LocalDate start4 = LocalDate.of(2024, 2, 27);
        LocalDate end4 = LocalDate.of(2024, 2, 28);
        Event event4 = new Event("Meeting", start4, end4);

        assertTrue(event1.compareTo(event2) < 0);
        assertTrue(event2.compareTo(event1) > 0);
        assertTrue(event2.compareTo(event3) == 0);
        assertTrue(event3.compareTo(event4) != 0);
    }

    @Test
    void testDeadlineCreation() {
        LocalDate due = LocalDate.of(2024, 10, 31);
        Deadline deadline = new Deadline("Finish project", due);
        
        assertNotNull(deadline);
        assertEquals("Finish project", deadline.getDescription());
        assertEquals("[D] [ ] Finish project (by: Oct 31 2024)", deadline.toString());
    }

    @Test
    void testDeadlineComparison() {
        LocalDate due1 = LocalDate.of(2024, 3, 1);
        LocalDate due2 = LocalDate.of(2024, 3, 15);
        LocalDate due3 = LocalDate.of(2024, 3, 10);

        Deadline deadline1 = new Deadline("Submit report", due1);
        Deadline deadline2 = new Deadline("Start project", due2);
        Deadline deadline3 = new Deadline("Assignment A", due3);
        Deadline deadline4 = new Deadline("Project B", due3);

        // Different due dates
        assertTrue(deadline1.compareTo(deadline2) < 0);
        assertTrue(deadline2.compareTo(deadline1) > 0);

        // Same due date, different descriptions
        assertTrue(deadline3.compareTo(deadline4) < 0);
        assertTrue(deadline4.compareTo(deadline3) > 0);

        // Comparison with itself (should be equal)
        assertEquals(0, deadline1.compareTo(deadline1));

        // Comparison across different due dates and descriptions
        assertTrue(deadline1.compareTo(deadline3) < 0);
        assertTrue(deadline3.compareTo(deadline2) < 0);
        assertTrue(deadline4.compareTo(deadline1) > 0);
    }
}
