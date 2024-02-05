package duke.tasks;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString(){
        assertEquals("[T][ ]read book", new ToDo("read book").toString());
    }
}