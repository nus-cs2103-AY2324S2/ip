package duke;

import georgie.Deadline;
import georgie.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void parseDateTest() {
        assertEquals(LocalDate.parse("2022-01-23"), Deadline.parseDate("2022-01-23"));
    }

    @Test
    public void addDeadlineTaskTest() throws DukeException {
        TaskList taskList = new TaskList();
        Deadline.addDeadlineTask(taskList, "Finish Assignment", "2022-01-23");
        assertEquals(1, taskList.size());
        assertEquals("[D] [ ] Finish Assignment (by: Jan 23 2022)",
                taskList.getTask(0).getStatusIcon());
    }

    @Test
    public void getStatusIconTest() {
        LocalDate dueByDate = LocalDate.parse("2022-01-23");
        Deadline deadline = new Deadline("Finish Assignment", dueByDate);

        assertEquals("[D] [ ] Finish Assignment (by: Jan 23 2022)",
                deadline.getStatusIcon().replaceAll("\\s+", " "));
    }

    @Test
    public void toFileStringTest() {
        LocalDate dueByDate = LocalDate.parse("2022-01-23");
        Deadline deadline = new Deadline("Finish Assignment", dueByDate);
        assertEquals("D | 0 | Finish Assignment | Jan 23 2022", deadline.toFileString());
    }

}
