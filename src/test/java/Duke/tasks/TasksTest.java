package duke.tasks;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidEventException;
import duke.main.Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TasksTest {
    @Test
    public void hasDescriptionToDo() {
        Task token = new ToDo("read book and sleep");
        assertEquals(true, token.descriptionHasWord("read book and sleep"));
    }
    @Test
    public void hasDescriptionDeadline() {
        try {
            Task token = new Deadline("read book", "19/10/2000 0000");
            assertEquals(false, token.descriptionHasWord("read book and sleep"));
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
    @Test
    public void hasDescriptionTestEvent() throws InvalidEventException {
        Task token = new Event("eat mochi", "20/12/2000 0000", "20/12/2000 0000");
        assertEquals(true, token.descriptionHasWord("eat mochi"));
    }
    @Test
    public void event_invalidDate_exceptionThrown() {
        try {
            Task test = new Event("eat", "20/12/2024 1800", "19/12/2024 2000");
            fail();
        } catch (InvalidEventException e) {
            String expected = "After I try so hard, you want to cook me by keying the date\n" +
                    "and time wrongly try again, by adding the event in the format:\n" +
                    "event [task description] /from: dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm\n" +
                    "/from date must be before /to date\n";
            assertEquals(expected, e.toString());
        }
    }
}
