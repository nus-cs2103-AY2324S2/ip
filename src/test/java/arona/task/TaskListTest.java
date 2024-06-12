package arona.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList sampleTaskList;

    @BeforeEach
    public void setSampleTaskList() {
        sampleTaskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new SampleTask());
        tasks.add(new SampleTask());
        tasks.add(new SampleTask());
        sampleTaskList.readTasksFromStorage(tasks);
    }

    @Test
    public void getSize_threeTasks_success() {
        assertEquals(sampleTaskList.getSize(), 3);
    }

    @Test
    public void addTask_success() {
        sampleTaskList.addElements(new SampleTask());
        assertEquals(sampleTaskList.getSize(), 4);
    }

    @Test
    public void deleteTask_success() {
        sampleTaskList.deleteElements(2);
        assertEquals(sampleTaskList.getSize(), 2);
    }

    @Test
    public void markTask_correctStatusIcon() {
        sampleTaskList.markIndexAsDone(2);
        assertEquals(sampleTaskList.getTask(2).getStatusIcon(), "[X]");
    }
}
