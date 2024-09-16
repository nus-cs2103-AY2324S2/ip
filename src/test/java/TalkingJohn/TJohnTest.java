package talkingjohn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TJohnTest {
    @Test
    public void testEvent() {
        Event event = new Event("project meeting ", "from Mon 2pm ", "to 4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void testEventNotes() {
        Event event = new Event("project meeting ", "check budget", "from Mon 2pm ", "to 4pm");
        assertEquals("[E][ ] project meeting [check budget](from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void testEventMark() {
        Event event = new Event("project meeting ", "from Mon 2pm ", "to 4pm");
        event.mark();
        assertEquals("[E][X] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void testEventUnMark() {
        Event event = new Event("project meeting ", "from Mon 2pm ", "to 4pm");
        event.mark();
        event.unMark();
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void testTodo() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testTodoNotes() {
        Todo todo = new Todo("Buy groceries", "Buy stationary");
        assertEquals("[T][ ] Buy groceries[Buy stationary]", todo.toString());
    }

    @Test
    public void testTodoMark() {
        Todo todo = new Todo("Buy groceries");
        todo.mark();
        assertEquals("[T][X] Buy groceries", todo.toString());
    }

    @Test
    public void testTodoUnMark() {
        Todo todo = new Todo("Buy groceries");
        todo.unMark();
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("Eat dinner ", "by 09/12/2001 1900");
        assertEquals("[D][ ] Eat dinner (by: Dec 09 2001 19:00)", deadline.toString());
    }

    @Test
    public void testDeadlineNotes() {
        Deadline deadline = new Deadline("Eat dinner ", "throw trash", "by 09/12/2001 1900");
        assertEquals("[D][ ] Eat dinner [throw trash](by: Dec 09 2001 19:00)", deadline.toString());
    }

    @Test
    public void testDeadlineMark() {
        Deadline deadline = new Deadline("Eat dinner ", "by 09/12/2001 1900");
        deadline.mark();
        assertEquals("[D][X] Eat dinner (by: Dec 09 2001 19:00)", deadline.toString());
    }

    @Test
    public void testDeadlineUnMark() {
        Deadline deadline = new Deadline("Eat dinner ", "by 09/12/2001 1900");
        deadline.mark();
        deadline.unMark();
        assertEquals("[D][ ] Eat dinner (by: Dec 09 2001 19:00)", deadline.toString());
    }
}
