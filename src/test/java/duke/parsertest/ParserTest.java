package duke.parsertest;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void createToDo_validInput_success() throws DukeException {
        Parser parser = new Parser();
        String input = "todo buy milk";
        
        Task result = parser.createToDo(input);
 
        assertTrue(result instanceof ToDos, "The result should be an instance of ToDos");
        assertEquals("buy milk", result.getDescription(), "The description of the todo should match the input");
    }
    
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

