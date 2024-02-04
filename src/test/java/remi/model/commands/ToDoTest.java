package remi.model.commands;

import org.junit.jupiter.api.Test;
import remi.model.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {

    @Test
    public void parsableString_dummyTask_returnString() {
        ToDo todo = new ToDo("dummy");

        assertEquals(todo.getParsableString(), "T|V|dummy");
    }
}
