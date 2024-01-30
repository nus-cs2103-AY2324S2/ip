package osiris.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InputsValidatorTest {

    @Test
    public void validateMarkTaskCompletedInputTest() {
        assertTrue(InputsValidator.getInstance().validateMarkTaskCompletedInput("mark 1"));

        assertFalse(InputsValidator.getInstance().validateMarkTaskCompletedInput("mark "));
        assertFalse(InputsValidator.getInstance().validateMarkTaskCompletedInput("mark 1.5"));
        assertFalse(InputsValidator.getInstance().validateMarkTaskCompletedInput("mark a"));
    }

    @Test
    public void validateMarkTaskIncompleteInputTest() {
        assertTrue(InputsValidator.getInstance().validateMarkTaskIncompleteInput("unmark 1"));

        assertFalse(InputsValidator.getInstance().validateMarkTaskIncompleteInput("unmark "));
        assertFalse(InputsValidator.getInstance().validateMarkTaskIncompleteInput("unmark 1.5"));
        assertFalse(InputsValidator.getInstance().validateMarkTaskIncompleteInput("unmark a"));
    }
    @Test
    public void validateRemoveTaskInputTest() {
        assertTrue(InputsValidator.getInstance().validateRemoveTaskInput("delete 1"));

        assertFalse(InputsValidator.getInstance().validateRemoveTaskInput("delete "));
        assertFalse(InputsValidator.getInstance().validateRemoveTaskInput("delete 1.5"));
        assertFalse(InputsValidator.getInstance().validateRemoveTaskInput("delete a"));
    }

    @Test
    public void validateAddToDoTaskInputTest() {
        assertTrue(InputsValidator.getInstance().validateAddToDoTaskInput("todo 1"));
        assertTrue(InputsValidator.getInstance().validateAddToDoTaskInput("todo Task 1"));
        assertTrue(InputsValidator.getInstance().validateAddToDoTaskInput("todo Valid Task 1"));
        assertFalse(InputsValidator.getInstance().validateAddToDoTaskInput("todo"));
        assertFalse(InputsValidator.getInstance().validateRemoveTaskInput("todo "));
    }

    @Test
    public void validateAddDeadlineTaskInputTest() {
        assertTrue(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline 1 /by 01-01-2024"));
        assertTrue(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 01-01-2024"));
        assertTrue(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task 1 /by 01-01-2024"));

        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 01/01/2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline /by 01-01-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 2024-01-01"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 32-01-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 01-13-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 1-01-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 01-1-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task /by 1-1-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task 01-01-2024"));
        assertFalse(InputsValidator.getInstance().validateAddDeadlineTaskInput("deadline Task by 01-01-2024"));
    }
    @Test
    public void validateAddEventTaskInputTest() {
        assertTrue(InputsValidator.getInstance()
                .validateAddEventTaskInput("event 2 /from 01-01-2024 1200 /to 01-01-2024 1300"));
        assertTrue(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting /from 01-01-2024 1200 /to 01-01-2024 1300"));

        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event /from 01-01-2024 1200 /to 01-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting from 01-01-2024 1200 to 01-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting /from 01-01-2024 /to 01-01-2024"));
        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting /from 01-01-2024 1300 /to 01-01-2024 1200"));
        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting 01-01-2024 1200 01-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting /from 32-01-2024 1200 /to 33-01-2024 1300"));
        assertFalse(InputsValidator.getInstance()
                .validateAddEventTaskInput("event School Meeting /from 01-13-2024 1200 /to 01-13-2024 1300"));
        //
        // And others ...
        //
    }
}
