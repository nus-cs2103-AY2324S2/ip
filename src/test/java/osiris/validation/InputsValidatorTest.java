package osiris.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for InputsValidator class.
 */
public class InputsValidatorTest {

    /**
     * Test for validating mark task completed input.
     */
    @Test
    public void validateMarkTaskCompletedInputTest() {
        // Tests for valid input
        assertTrue(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark 1"));

        // Tests for invalid inputs
        assertFalse(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark "));
        assertFalse(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark 1.5"));
        assertFalse(InputsValidator.getInstance().isMarkTaskCompleteInputValid("mark a"));
    }

    /**
     * Test for validating mark task incomplete input.
     */
    @Test
    public void validateMarkTaskIncompleteInputTest() {
        // Tests for valid input
        assertTrue(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark 1"));

        // Tests for invalid inputs
        assertFalse(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark "));
        assertFalse(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark 1.5"));
        assertFalse(InputsValidator.getInstance().isMarkTaskIncompleteInputValid("unmark a"));
    }

    /**
     * Test for validating remove task input.
     */
    @Test
    public void validateRemoveTaskInputTest() {
        // Tests for valid input
        assertTrue(InputsValidator.getInstance().isDeleteTaskInputValid("delete 1"));

        // Tests for invalid inputs
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("delete "));
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("delete 1.5"));
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("delete a"));
    }

    /**
     * Test for validating add to-do task input.
     */
    @Test
    public void validateAddToDoTaskInputTest() {
        // Tests for valid input
        assertTrue(InputsValidator.getInstance().isAddToDoTaskInputValid("todo 1"));
        assertTrue(InputsValidator.getInstance().isAddToDoTaskInputValid("todo Task 1"));
        assertTrue(InputsValidator.getInstance().isAddToDoTaskInputValid("todo Valid Task 1"));

        // Tests for invalid inputs
        assertFalse(InputsValidator.getInstance().isAddToDoTaskInputValid("todo"));
        assertFalse(InputsValidator.getInstance().isDeleteTaskInputValid("todo "));
    }

    /**
     * Test for validating add deadline task input.
     */
    @Test
    public void validateAddDeadlineTaskInputTest() {
        // Tests for valid input
        assertTrue(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline 1 /by 01-01-2024"));
        assertTrue(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task /by 01-01-2024"));
        assertTrue(InputsValidator.getInstance().isAddDeadlineTaskInputValid("deadline Task 1 /by 01-01-2024"));

        // Tests for invalid inputs
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
     * Test for validating add event task input.
     */
    @Test
    public void validateAddEventTaskInputTest() {
        // Tests for valid input
        assertTrue(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event 2 /from 01-01-2024 1200 /to 01-01-2024 1300"));
        assertTrue(InputsValidator.getInstance()
                .isAddEventTaskInputValid("event School Meeting /from 01-01-2024 1200 /to 01-01-2024 1300"));

        // Tests for invalid inputs
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

    //
    // And more...
    //
}
