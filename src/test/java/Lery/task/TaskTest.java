package Lery.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetStatusIcon(){
        assertEquals(" ", new Task("read book").getStatusIcon());
    }
    @Test
    public void testGetDescription(){
        assertEquals("read book", new Task("read book").getDescription());
    }
    @Test
    public void testMarkAsDone(){
        assertEquals("Nice! I've marked this task as done:\n" + "[X] " + "read book", new Task("read book").markAsDone());
    }
    @Test
    public void testUnmarkAsDone(){
        Task t = new Task("read book");
        t.markAsDone();
        assertEquals("OK, I've marked this task as not done yet:\n" + "[ ] " + "read book", t.markAsDone());
    }
    @Test
    public void testGetType(){
        assertEquals("", new Task("read book").getType());
    }

    @Test
    public void testGetExtraInfo(){
        assertEquals("", new Task("read book").getExtraInfo());
    }
    @Test
    public void testGetExtraInfoShortened(){
        assertEquals("", new Task("read book").getExtraInfoShortened());
    }
}
