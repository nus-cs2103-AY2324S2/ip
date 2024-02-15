package TalkingJohn;

import org.junit.jupiter.api.Test;

import talkingjohn.Event;
import talkingjohn.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TJohnTest {

    @Test
    public void testEventToString() {
        Event event = new Event("project meeting ", "from Mon 2pm ", "to 4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", event.toString());
    }

    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }
    // Add more tests as needed

}
