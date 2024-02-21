package fireraya.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTostringTest(){
        Task t = new Todo("Hello!");
        String str = t.toString();
        assertEquals("[T][ ] Hello!", str);
    }

    @Test
    public void saveFormatTest(){
        Task t = new Todo("Eat bread");
        String str = t.saveFormat();
        assertEquals("T|0|Eat bread", str);
    }

    @Test
    public void markDoneTest(){
        Task t = new Todo("Do stuff");
        t.markAsDone();
        String str = t.saveFormat();
        assertEquals("T|1|Do stuff", str);
    }
}
