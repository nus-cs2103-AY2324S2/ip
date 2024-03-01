package nihao.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nihao.action.task.DeadlineTask;
import nihao.action.task.EventTask;
import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;

class DataHandlerTest {
    private String dateTime = "10/02/2024 2000";
    private LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    private DeadlineTask deadlineTask = new DeadlineTask("homework", localDateTime);
    private EventTask eventTask = new EventTask("concert", localDateTime, localDateTime);
    private Task[] taskArray = {deadlineTask, eventTask};
    private ArrayList<Task> lst = new ArrayList<>(Arrays.asList(taskArray));

    @Test
    void handleData() {
        ArrayList<Task> copy = DataHandler.tasks;
        ArrayList<Task> before = DataHandler.tasks;
        DataHandler.addTask(eventTask);
        before.add(eventTask);
        assertEquals(before, DataHandler.tasks);
        DataHandler.tasks = copy;
    }

    @Test
    void getData() {
        assertEquals(DataHandler.tasks, DataHandler.getData());
    }

    @Test
    void size() {
        assertEquals(DataHandler.tasks.size(), DataHandler.size());
    }

    @Test
    void getTask_success() throws IndexOutOfBoundsException {
        if (DataHandler.size() >= 1) {
            assertEquals(DataHandler.tasks.get(0), DataHandler.getTask(1));
        }
    }

    @Test
    void getTask_exception() {
        int index = DataHandler.size() + 1;
        try {
            DataHandler.getTask(index);
        } catch (Exception e) {
            assertEquals("IndexOutOfBoundsException: " + "index " + index + " out of bounds for size "
                    + DataHandler.size() + ".", e.getMessage());
        }
    }
}
