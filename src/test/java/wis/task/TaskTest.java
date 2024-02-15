package wis.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private class TaskStub extends Task {
        public TaskStub(String description) {
            super(description);
        }
    }

    @Test
    public void toSavedStringTest() {
        TaskStub task = new TaskStub("someTask");
        assertEquals("0#!#someTask", task.toSavedString());
        task.setDone();
        assertEquals("1#!#someTask", task.toSavedString());
        task.setUndone();
        assertEquals("0#!#someTask", task.toSavedString());
    }
}