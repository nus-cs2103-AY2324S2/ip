package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        String order = "writing"
        Todo todo = new Todo("writing")
        assertEquals("writing", todo.toString());
    }
}