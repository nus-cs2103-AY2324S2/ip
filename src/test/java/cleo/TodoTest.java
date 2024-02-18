
package cleo;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void creatingTodo_ShouldHaveCorrectDescription() {
        Todo todo = new Todo("Read a book");
        assertEquals("Read a book", todo.getDescription());
    }

    @Test
    public void toString_ShouldReturnCorrectFormat() {
        Todo todo = new Todo("Read a book");
        String expectedOutput = "[T][ ] Read a book"; // Assuming this is your expected format
        assertEquals(expectedOutput, todo.toString());
    }


}
