package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import iggly.model.Deadline;
import iggly.model.Event;
import iggly.model.ToDo;

public class TaskTest {
    private static ToDo todo;
    private static Event event;
    private static Deadline deadline;

    @BeforeAll
    static void setUp() {
        todo = new ToDo("todo");
        event = new Event("event", LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 1, 1, 12, 0));
        deadline = new Deadline("deadline", LocalDateTime.of(2024, 1, 1, 12, 0));
    }

    @Test
    public void todoTest() {
        String expectedToString = "[T][ ] todo";
        String actualToString = todo.toString();

        assertInstanceOf(ToDo.class, todo);
        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void eventTest() {
        String expectedToString = "[E][ ] event (on: 01 Jan 2024 12:00AM to 12:00PM)";
        String actualToString = event.toString();

        assertInstanceOf(Event.class, event);
        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void deadlineTest() {
        String expectedToString = "[D][ ] deadline (by: 01 Jan 2024 12:00PM, MONDAY)";
        String actualToString = deadline.toString();

        assertInstanceOf(Deadline.class, deadline);
        assertEquals(expectedToString, actualToString);
    }
}
