package task;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        Task todoWithDescription = new Task("test todo description");
        String tString1 = todoWithDescription.toString();
        assertEquals("[ ] test todo description", tString1);
    }

    @Test
    public void testMarkDoneUnmarkDone() {
        Task task = new Task("test todo mark/unmark");
        
        task.markAsDone();
        String tString1 = task.toString();
        assertEquals("[X] test todo mark/unmark", tString1);

        task.markAsUndone();
        String tString2 = task.toString();
        assertEquals("[ ] test todo mark/unmark", tString2);
    }
}
