package pyrite.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_normalString_success(){
        ToDo todo = new ToDo ("test");
        assertEquals("[T][ ] test", todo.toString());
    }
    @Test
    public void toString_emptyString_success(){
        ToDo todo = new ToDo ("");
        assertEquals("[T][ ] ", todo.toString());
    }
    @Test
    public void toString_stringWithSpaces_success(){
        ToDo todo = new ToDo ("test test");
        assertEquals("[T][ ] test test", todo.toString());
    }
    @Test
    public void toString_markedAsDone_success(){
        ToDo todo = new ToDo ("test");
        todo.setStatus(Task.Status.DONE);
        assertEquals("[T][X] test", todo.toString());
    }
}
