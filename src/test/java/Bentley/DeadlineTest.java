package Bentley;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineToStringFormat() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addDeadlineTask("deadline return books by/ 2025-09-09");
        assertEquals(1, taskList.getTasks().size());
        assertEquals("[X] (D) return books | Sep 09 2025", taskList.getTasks().get(0).toString());
    }

    @Test
    public void taskMarkAsDoneToStringFormat() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addDeadlineTask("deadline return book by/ 2025-09-09");
        taskList.markAsDone("done 1");

        assertEquals("[X] (D) return book | Sep 09 2025", taskList.getTasks().get(0).toString());
    }
}