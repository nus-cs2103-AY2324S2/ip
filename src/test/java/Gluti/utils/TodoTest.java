package Gluti.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest(){
        Todo testTodo = new Todo("todoTest");
        assertEquals(testTodo.toString(), "[T][ ] todoTest");
    }
}