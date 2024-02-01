package chatbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    
    @Test
    public void checkInstantiation() {
        TodoTask todo = new TodoTask("read book");
        String expectedString = "[T][ ] read book";
        assertEquals(expectedString, todo.toString());
    }

    @Test 
    public void checkMark() {
        TodoTask todo = new TodoTask("read book");
        todo.mark();
        String expectedString = "[T][X] read book";
        assertEquals(expectedString, todo.toString());
    }

    @Test
    public void checkUnmark() {
        TodoTask todo = new TodoTask("read book");
        todo.mark();
        todo.unmark();
        String expectedString = "[T][ ] read book";
        assertEquals(expectedString, todo.toString());
    }

    @Test
    public void checkExportToSave() {
        TodoTask todo = new TodoTask("read book");
        String expectedString = "T,0,read book";
        assertEquals(expectedString, todo.exportToSave());
    }

}
