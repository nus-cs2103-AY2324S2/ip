package damon.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoTest(){
        assertEquals("[T][ ] buy books", new ToDo("buy books").toString());
        assertEquals("[T][ ] buy books",
                new ToDo("buy books", false).toString());
        assertEquals("[T][X] buy books",
                new ToDo("buy books", true).toString());
    }


}

