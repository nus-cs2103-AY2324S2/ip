package duke.tasks;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createCorrectTest(){
        String taskName = "a task";
        try {
            Deadline testDeadline = new Deadline(taskName, "2022-12-31");
            assertEquals("[D] [ ] a task (by: Dec 31 2022)", testDeadline.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createWrongTest(){
        String taskName = "a task";
        try {
            Deadline testDeadline = new Deadline(taskName, "huh");
        } catch (DukeException e) {
            assertEquals(
                    "Invalid input format for date :( Please use the format yyyy-mm-dd instead!",
                    e.getMessage());
        }
    }
}
