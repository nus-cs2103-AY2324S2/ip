package task;

import exceptions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    public void test1() throws LuluException {
        Task task = new Todo("test1");

        assertEquals(false, task.getStatus());
    }

    @Test
    public void test2() throws LuluException {
        Task task = new Todo("test2");
        task.updateStatus(true);

        assertEquals(true, task.getStatus());
    }

    @Test
    public void test3() throws LuluException {
        Task task = new Todo("test3");

        assertThrows(InvalidStatusUpdateException.class, () -> task.updateStatus(false));
    }

    @Test
    public void test4() {
        Task task = new Deadline("test4", LocalDate.parse("2022-01-01"));
        boolean query = task.queryByDate(LocalDate.parse("2022-01-01"));

        assertEquals(true, query);
    }

    @Test
    public void test5() {
        Task task = new Deadline("test5", LocalDate.parse("2022-01-01"));
        boolean query = task.queryByDate(LocalDate.parse("2021-01-01"));

        assertEquals(false, query);
    }

    @Test
    public void test6() {
        Task task = new Event("test6", LocalDate.parse("2020-01-01"), LocalDate.parse("2022-01-01"));
        boolean query = task.queryByDate(LocalDate.parse("2021-01-01"));

        assertEquals(true, query);
    }

    @Test
    public void test7() {
        Task task = new Event("test7", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-01-01"));
        boolean query = task.queryByDate(LocalDate.parse("2020-01-01"));

        assertEquals(false, query);
    }
}
