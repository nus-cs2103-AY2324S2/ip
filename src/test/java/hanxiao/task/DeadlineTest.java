package hanxiao.task;

import hanxiao.exception.HanxiaoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void test_getTaskTypeIcon() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));

        assertEquals("D",ddl.getTaskTypeIcon());
    }

    @Test
    void test_getTaskType() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));

        assertEquals("deadline",ddl.getTaskType());
    }

    @Test
    void test_isTimeForStart() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2040-02-15"));

        assertEquals(true,ddl.isTimeForStart(LocalDate.now()));
    }

    @Test
    void test_equals() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Deadline ddll = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Deadline ddlll = new Deadline("Trivial description", LocalDate.parse("2024-02-16"));

        assertEquals(true,ddl.equals(ddll));
        assertEquals(false,ddll.equals(ddlll));
    }

    @Test
    void test_compareTo() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Deadline ddll = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Deadline ddlll = new Deadline("Trivial description", LocalDate.parse("2024-02-16"));

        assertEquals(true,ddl.compareTo(ddlll)<0);
        assertEquals(true,ddl.compareTo(ddll)==0);
    }

    @Test
    void test_updateTask_throwHanxiaoException() throws HanxiaoException {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Deadline ddll = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        Deadline ddlll = new Deadline("Trivial description", LocalDate.parse("2024-02-16"));
        ddl.updateTask("/by", "2024-02-16");

        assertEquals(true,ddl.compareTo(ddlll)==0);
        assertEquals(true,ddl.compareTo(ddll)>0);
    }

    @Test
    void test_getByTime() {
        Deadline ddl = new Deadline("Trivial description", LocalDate.parse("2024-02-15"));
        LocalDate got = ddl.getByTime();
        assertEquals(LocalDate.parse("2024-02-15"),got);
    }
}
