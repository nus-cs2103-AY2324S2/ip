package hal.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        assertEquals(new Todo(false,"Do Homework").toString(),
                "[T][ ] Do Homework");
        assertEquals(new Todo(true,"Read Book").toString(),
                "[T][X] Read Book");
    }

    @Test
    public void getFileStringTest(){
        assertEquals(new Todo(false,"Do Homework").getFileString(),
                "T | 0 | Do Homework");
        assertEquals(new Todo(true,"Read Book").getFileString(),
                "T | 1 | Read Book");
    }
}
