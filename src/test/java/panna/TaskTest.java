package panna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Parser parser = new Parser("yyyy-MM-dd");

    @Test
    public void equalsTodoTest() {
        Task t = new Todo("Hello");
        Task t2 = new Todo("Hello");
        assertEquals(t, t2);
    }

    @Test
    public void equalsDeadlineTest() {
        Task t3 = new Deadline("Test", parser.parse("2022-07-09"));
        Task t4 = new Deadline("Test", parser.parse("2022-07-09"));
        assertEquals(t3, t4);

    }

    @Test
    public void equalsEventTest() {
        Task t5 = new Event("Test", parser.parse("2022-07-09"), parser.parse("2023-01-31"));
        Task t6 = new Event("Test", parser.parse("2022-07-09"), parser.parse("2023-01-31"));
        assertEquals(t5, t6);

    }

    @Test
    public void notEqualsTodoTest() {
        Task t = new Todo("Hello");
        Task t2 = new Todo("Bye");
        assertNotEquals(t, t2);
    }

    @Test
    public void notEqualsDeadlineTest() {
        Task t3 = new Deadline("Test", parser.parse("2022-07-09"));
        Task t4 = new Deadline("Test", parser.parse("2022-07-10"));
        assertNotEquals(t3, t4);

    }

    @Test
    public void notEqualsEventTest() {
        Task t5 = new Event("Test", parser.parse("2022-07-09"), parser.parse("2023-01-31"));
        Task t6 = new Event("Test", parser.parse("2022-07-10"), parser.parse("2023-01-31"));
        assertNotEquals(t5, t6);

    }




}
