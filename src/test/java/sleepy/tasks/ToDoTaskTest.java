package sleepy.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    @Test
    public void getDescription_normalToDo_success() {
        ToDoTask testToDoTask = new ToDoTask("write user guide");
        assertEquals("[T][ ] write user guide", testToDoTask.getDescription());
    }
}
