package Ping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void todoCommand_ShouldHaveCorrectFormat() {
        Todo todo = new Todo("shower");
        assertEquals("shower", todo.description);
    }

    @Test
    public void String_ShouldBeCorrect() {
        Todo todo = new Todo("shower");
        String correctAnswer = "[T][ ] shower";
        assertEquals(correctAnswer, todo.toString());
    }
}
