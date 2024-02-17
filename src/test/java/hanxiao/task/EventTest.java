package hanxiao.task;

import hanxiao.exception.HanxiaoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void test_getTaskTypeIcon() {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));

        assertEquals("E",event.getTaskTypeIcon());
    }

    @Test
    void test_getTaskType() {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));

        assertEquals("event",event.getTaskType());
    }

    @Test
    void test_isTimeForStart() {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));

        assertEquals(true,event.isTimeForStart(LocalDate.now()));
    }

    @Test
    void test_equals() {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));
        Event eevent = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));
        Event eventt = new Event("Trivial description", LocalDate.parse("2024-02-19"), LocalDate.parse("2025-02-15"));


        assertEquals(true,event.equals(eevent));
        assertEquals(false,event.equals(eventt));
    }

    @Test
    void test_compareTo() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));
        Event eventt = new Event("Trivial description", LocalDate.parse("2024-02-19"), LocalDate.parse("2025-02-15"));

        assertEquals(true,event.compareTo(eventt)<0);
        assertEquals(true,eventt.compareTo(ddl)>0);
    }

    @Test
    void test_updateTask_throwHanxiaoException() throws HanxiaoException {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));
        Event eventt = new Event("Trivial description", LocalDate.parse("2024-02-19"), LocalDate.parse("2025-02-15"));
        event.updateTask("/from", "2024-02-22");

        assertEquals(true,event.compareTo(eventt)>0);
    }

    @Test
    void test_getFromTime() {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));
        LocalDate got = event.getFromTime();
        assertEquals(LocalDate.parse("2024-02-15"),got);
    }

    @Test
    void test_getToTime() {
        Event event = new Event("Trivial description", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));
        LocalDate got = event.getToTime();
        assertEquals(LocalDate.parse("2025-02-15"),got);
    }
}
