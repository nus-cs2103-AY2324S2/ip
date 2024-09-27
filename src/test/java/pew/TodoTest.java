package pew;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void todo_createTask_success() throws PewException {
        assertEquals("1. [T][ ] pray", new ToDo(0, "pray").getTask());
    }

    @Test
    public void todo_createTask_invalidInput(){
        try {
            String userInput = "todo";
            PewException.validateToDo(userInput);
            assertEquals("1. [T][ ]", Parser.parseUserInput(userInput, new TaskList(new ArrayList<>())));
        } catch (PewException d) {
            assertEquals("ToDo Task Missing!", d.getMessage());
        }
    }
}