package tars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void taskCreation(){
        Todo task = new Todo("description");
        assertEquals(task.toString(), "[T][ ] description");
    }


}
