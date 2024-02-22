package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void rmvTasks_invalidInputs_exceptionThrown() {
        TaskList taskList = new TaskList();

        try {
            taskList.rmvTask(-1); // attempt to remove last task
            fail();
        } catch (DukeException e){
            assertEquals("   Hmm, there isn't an available task to delete."
                    , e.getMessage());
        }
    }

    @Test
    public void getTasks_invalidInputs_exceptionThrown(){
        TaskList taskList = new TaskList();

        try {
            taskList.getTask(1); // attempt to retrieve non-existent task
            fail();
        } catch (DukeException e){
            assertEquals("   Hmm, this task is not available/ does not exist."
                    , e.getMessage());
        }
    }
}
