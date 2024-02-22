package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        Todo todo = new Todo("read");
        assertEquals("[T][ ] read", todo.toString());
    }
}
