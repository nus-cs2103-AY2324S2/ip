package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.TaylorException;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class EditTaskTest {
    @Test
    public void insertSuccess() throws TaylorException {
        List<List<Task>> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            taskList.add(new ArrayList<>());
        }
        String command = "event The Eras Tour Singapore /from 2024-03-02 1900 /to 2024-03-09 2200";
        String result = InsertTask.execInsertTask(command, taskList).toString();
        assertEquals("With gentle hands, I've added you,\n"
                + "To the story of skies so blue.\n"
                + "[E][ ] The Eras Tour Singapore (from:02 Mar 2024 07:00PM to: 09 Mar 2024 10:00PM)\n"
                + "Now you have 1 tasks in the list.\n", result);
    }
}
