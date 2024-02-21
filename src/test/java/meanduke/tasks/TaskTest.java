package meanduke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void saveStringEventTest() {
        Event event = new Event("testDesc",
                true,
                LocalDate.parse("2023-12-12"),
                LocalTime.parse("23:23"),
                LocalDate.parse("2023-01-01"),
                LocalTime.parse("01:01"));
        assertEquals("EVENT\ntestDesc\ntrue\n2023-12-12;23:23\n2023-01-01;01:01", event.saveString());
    }

    @Test
    public void saveStringDeadlineTest() {
        Deadline deadline = new Deadline("testDesc",
                true,
                LocalDate.parse("2023-12-12"),
                LocalTime.parse("23:23"));
        assertEquals("DEADLINE\ntestDesc\ntrue\n2023-12-12;23:23", deadline.saveString());
    }

    @Test
    public void saveStringTodoTest() {
        ToDo todo = new ToDo("testDesc testtest", true);
        assertEquals("TODO\ntestDesc testtest\ntrue", todo.saveString());
    }
}
