package osiris.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for InputsValidator class.
 */
public class InputsValidatorTest {

    /**
     * Tests the validity of input for marking a task as complete.
     */
    @Test
    public void isMarkTaskCompleteInputValid_validInput_trueReturned() {
        assertTrue(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark 1"));
    }

    /**
     * Tests invalid inputs for marking a task as complete.
     */
    @Test
    public void isMarkTaskCompleteInputValid_invalidInputs_falseReturned() {
        assertFalse(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark "));
        assertFalse(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark 1.5"));
        assertFalse(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark a"));
    }

    /**
     * Tests the validity of input for marking a task as incomplete.
     */
    @Test
    public void isMarkTaskIncompleteInputValid_validInput_trueReturned() {
        assertTrue(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark 1"));
    }

    /**
     * Tests the validity of input for marking a task as incomplete.
     */
    @Test
    public void isMarkTaskIncompleteInputValid_invalidInput_falseReturned() {
        assertFalse(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark "));
        assertFalse(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark 1.5"));
        assertFalse(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark a"));
    }

    /**
     * Tests the validity of input for deleting a task.
     */
    @Test
    public void isDeleteTaskInputValid_validInput_trueReturned() {
        assertTrue(InputsValidator.getInstance().isDeleteTaskInputValid("delete 1"));
    }

    /**
     * Tests invalid input for deleting a task.
     */
    @Test
    public void isDeleteTaskInputValid_invalidInput_falseReturned() {
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("delete "));
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("delete 1.5"));
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("delete a"));
    }

    /**
     * Tests the validity of input for adding a ToDo task.
     */
    @Test
    public void isAddToDoTaskInputValid_validInput_trueReturned() {
        assertTrue(InputsValidator.getInstance().isAddToDoTaskInputValid("todo 1"));
        assertTrue(InputsValidator.getInstance().isAddToDoTaskInputValid("todo Task 1"));
        assertTrue(InputsValidator.getInstance().isAddToDoTaskInputValid("todo Valid Task 1"));
    }

    /**
     * Tests invalid input for adding a ToDo task.
     */
    @Test
    public void isAddToDoTaskInputValid_invalidInput_falseReturned() {
        assertFalse(InputsValidator.getInstance().isAddToDoTaskInputValid("todo"));
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("todo "));
    }

    /**
     * Tests the validity of input for adding a deadline task.
     */
    @Test
    public void isAddDeadlineTaskInputValid_validInput_trueReturned() {
        assertTrue(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline 1 /by 01-01-2024"));
        assertTrue(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 01-01-2024"));
        assertTrue(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task 1 /by 01-01-2024"));
    }

    /**
     * Tests invalid input for adding a deadline task.
     */
    @Test
    public void isAddDeadlineTaskInputValid_invalidInput_falseReturned() {
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 01/01/2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline /by 01-01-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 2024-01-01"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 32-01-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 01-13-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 1-01-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 01-1-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 1-1-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task 01-01-2024"));
        assertFalse(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task by 01-01-2024"));
    }

    /**
     * Tests the validity of input for adding an event task.
     */
    @Test
    public void isAddEventTaskInputValid_validInput_trueReturned() {
        assertTrue(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event 2 /from 01-01-2024 1200 /to 01-01-2024 1300"));
        assertTrue(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting /from 01-01-2024 1200 /to 01-01-2024 1300"));
    }

    /**
     * Tests invalid input for adding an event task.
     */
    @Test
    public void isAddEventTaskInputValid_invalidInput_falseReturned() {
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event /from 01-01-2024 1200 /to 01-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting from 01-01-2024 1200 to 01-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting /from 01-01-2024 /to 01-01-2024"));
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting /from 01-01-2024 1300 /to 01-01-2024 1200"));
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting 01-01-2024 1200 01-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting /from 32-01-2024 1200 /to 33-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting /from 01-13-2024 1200 /to 01-13-2024 1300"));
    }
}
