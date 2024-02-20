package eve.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    //3 test to test if todo is created correctly or not
    
    @Test
    public void test1(){
        String input = "todo return book";
        Todo todo = new Todo(input);
        assertEquals("todo return book", todo.getTask());
    }

    @Test
    public void test2(){
        String input = "todo return book";
        Todo todo = new Todo(input);    
        todo.markAsDone();
        assertEquals(true, todo.getTaskStatus());

    }

    @Test
    public void test3(){
        String input = "todo return book";
        Todo todo = new Todo(input);    
        todo.markAsNotDone();
        assertEquals(false, todo.getTaskStatus());
        
    }
    

}
