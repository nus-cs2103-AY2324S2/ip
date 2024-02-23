package riri;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoCreationTest1(){
        Todo task = new Todo("nothing");
        assertEquals(task.toString(), "[T][ ] nothing");
    }
}