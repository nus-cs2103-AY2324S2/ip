package nicole.task;

import nicole.task.Todo;
import nicole.nicoleexceptions.NicoleException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class TodoTest {
    @Test
    void todo_noDescriptionGiven_nicoleExceptionThrown() {
        try {
            new Todo("null");
        } catch (NicoleException e) {
            assertEquals("ERROR. What exactly do you want me to note...tell me in the form of: todo [task]",
                    e.toString());
        }
    }

    @Test
    void todoCreation_perfectConditions_toStringCorrect() {
        try {
            Todo testTodo = new Todo("borrow book");
            assertEquals("[T][I] borrow book", testTodo.toString());
        } catch (NicoleException e) {
            fail();
        }
    }
}
