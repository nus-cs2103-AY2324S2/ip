package GandalfBot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    ToDos testTask = new ToDos("read a book");
    @Test
    public void toStringTest(){
        assertEquals(testTask.toString(), "[T][ ] read a book");
    }

}
