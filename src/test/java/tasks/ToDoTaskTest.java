package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fredricksen.Fredricksen;
import fredricksen.commands.UnknownCommand;
import fredricksen.tasks.ToDo;

public class ToDoTaskTest {
    private Fredricksen fredricksen = new Fredricksen();
    @Test
    public void toDoToString_correctFormat_success() {
        ToDo newTask = new ToDo("todo homework", "T", false);
        assertEquals("[T][] homework", newTask.toString());
    }

    @Test
    public void toDoToString_incorrectFormat_failure() {
        String command = "tdo homework";
        assertEquals(new UnknownCommand().execute(), fredricksen.getResponse(command));
    }
}
