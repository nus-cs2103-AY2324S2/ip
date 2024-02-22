package zoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testEventStatus() {
        //test that input matches the required save format when it is newly created
        Event testEvent = new Event("ip meeting /from 2pm /to 6pm");
        assertEquals("[E][ ] ip meeting (from: 2pm to: 6pm)", testEvent.getStatus());
    }

    @Test
    public void testMarkedEventStatus() {
        //test that input matches the required save format when it is newly created
        Event testEvent = new Event("ip meeting /from 2pm /to 6pm", "1");
        assertEquals("[E][X] ip meeting (from: 2pm to: 6pm)", testEvent.getStatus());
    }

    @Test
    public void testEventStatusNoSpaces() {
        //test that input matches the required save format when it is newly created
        Event testEvent = new Event("ip meeting /from2pm/to6pm");
        assertEquals("[E][ ] ip meeting (from: 2pm to: 6pm)", testEvent.getStatus());
    }

    @Test
    public void testEventStatusManySpaces() {
        //test that input matches the required save format when it is newly created
        Event testEvent = new Event("ip meeting /from     2pm         /to       6pm       ");
        assertEquals("[E][ ] ip meeting (from: 2pm to: 6pm)", testEvent.getStatus());
    }

}
