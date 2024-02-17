package teemo; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void test1() {
        Task a = new ToDo("TaskName");
        assertEquals(a.toString(), "[T][ ] TaskName");
    }

    @Test
    public void test2() {
        Task b = new ToDo("Task2");
        b.mark();
        assertEquals(b.toString(), "[T][X] Task2");
    }
}
