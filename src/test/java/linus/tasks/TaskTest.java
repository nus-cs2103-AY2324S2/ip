package linus.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import linus.tasks.Deadline;
import linus.tasks.Task;

public class TaskTest {
    
    @Test
    public void testMarkToString() {
        Task task = new Deadline("Do homework", "19/03/2020 1900");
        task.markAsDone();
        assertEquals("[D][X] Do homework (by: Date: 19 Mar 2020 Time: 7:00 PM)", task.toString());
    }

}
