package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getStatusIcon_success(){
        // status will be empty when isCompleted is false
        assertEquals("[ ]", new Todo("todo borrow books", false).getStatusIcon());

        // status will be X when isCompleted is true
        assertEquals("[X]", new Todo("todo borrow books", true).getStatusIcon());
    }

    @Test
    public void trimDescription_success(){
        // normally the command todo is removed from the string
        assertEquals("complete work", new Todo("todo complete work", false).trimDescription("todo complete work"));
    }

    @Test
    public void getTaskDescription_success() {
        // will give a string description that doesn't have todo word
        assertEquals("go for run", new Todo("todo go for run", true).getTaskDescription());
    }
}
