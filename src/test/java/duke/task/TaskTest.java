package duke.task;

import duke.parser.MissingInputFieldException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void nextWords() {
    }

    @Test
    void createTask_createThreeTypeOfTasks() {
        try {
            Task todo = Task.createTask("todo",
                    "todo test");
            assert todo != null;
            assertEquals(todo.getClass(), ToDo.class);

            Task deadline = Task.createTask("deadline",
                    "deadline test /by 111111T1111");
            assert deadline != null;
            assertEquals(deadline.getClass(), Deadline.class);

            Task event = Task.createTask("event",
                    "event test /from 111111T1111 /to 111111T1111");
            assert event != null;
            assertEquals(event.getClass(), Event.class);

            Task notTask = Task.createTask("invalidInput",
                    "nothing");
            assertNull(notTask);
        } catch (MissingInputFieldException e) {
            fail(); // tasks should be created accordingly
        }
    }

    @Test
    void getDescription() {
    }

    @Test
    void setIsDone() {
    }

    @Test
    void getIsDoneStatus() {
    }

    @Test
    void updateIsDoneMessage() {
    }

    @Test
    void parseInput() {
    }

    @Test
    void convertToDataRow() {
    }

    @Test
    void printType() {
    }

    @Test
    void setUpTask() {
    }

    @Test
    void convertDataToTask() {
    }
}