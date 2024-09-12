package duke.parsertest;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the functionality of the Parser class.
 * Ensures that tasks are created correctly from user input strings
 * and that appropriate exceptions are thrown for invalid inputs.
 */
public class ParserTest {
    
    /**
     * Tests the creation of a ToDo task with valid input.
     * Ensures that the task is created correctly and matches the expected description.
     *
     * @throws DukeException If an error occurs during task creation.
     */
    @Test
    public void createToDo_validInput_success() throws DukeException {
        Parser parser = new Parser();
        String input = "todo buy milk";
        
        Task result = parser.createToDo(input);
 
        assertTrue(result instanceof ToDo, "The result should be an instance of ToDos");
        assertEquals("buy milk", result.getDescription(), "The description of the todo should match the input");
    }
    
    /**
     * Tests the creation of a ToDo task with invalid input.
     * Expects the method to throw a StringIndexOutOfBoundsException due to missing task description.
     */
    @Test
    public void createToDo_invalidInput_throwsException() {
        Parser parser = new Parser();
        String input = "todo";
        
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            parser.createToDo(input);
        }, "Expected createToDo to throw StringIndexOutOfBoundException as task description is empty" +
                "but it did not");
        
    }
    
}

