package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for the PriorityComparator class.
 */
public class PriorityComparatorTest {

    private PriorityComparator priorityComparator;

    /**
     * Constructs a new PriorityComparatorTest instance.
     */
    public PriorityComparatorTest() {
        priorityComparator = new PriorityComparator();
    }

    /**
     * Tests the comparison of two tasks with different priorities.
     */
    @Test
    public void compareTwoTasksWithPriority() {
        Task highPriorityTask = new ToDo("High Priority Task");
        highPriorityTask.setPriority(Priority.HIGH);

        Task mediumPriorityTask = new Deadline("Medium Priority Task", null);
        mediumPriorityTask.setPriority(Priority.MEDIUM);

        int result = priorityComparator.compare(highPriorityTask, mediumPriorityTask);
        assertEquals(-1, result, "High priority task should be ranked higher than medium priority task");
    }

    /**
     * Tests the comparison of two tasks without priority.
     */
    @Test
    public void compareTwoTasksWithoutPriority() {
        Task task1 = new ToDo("Task 1");
        Task task2 = new Event("Task 2", null, null);

        int result = priorityComparator.compare(task1, task2);
        assertEquals(0, result, "Both tasks without priority should be considered equal");
    }

    /**
     * Tests the comparison of two tasks with the same priority.
     */
    @Test
    public void compareTasksWithSamePriority() {
        Task highPriorityTask1 = new ToDo("High Priority Task 1");
        highPriorityTask1.setPriority(Priority.HIGH);

        Task highPriorityTask2 = new Deadline("High Priority Task 2", null);
        highPriorityTask2.setPriority(Priority.HIGH);

        int result = priorityComparator.compare(highPriorityTask1, highPriorityTask2);
        assertEquals(0, result, "Both tasks with the same priority should be considered equal");
    }

    /**
     * Tests the comparison of two tasks with null priority.
     */
    @Test
    public void compareTasksWithNullPriority() {
        Task task1 = new ToDo("Task 1");
        Task task2 = new Deadline("Task 2", null);

        int result = priorityComparator.compare(task1, task2);
        assertEquals(0, result, "Tasks with null priority should be considered equal");
    }

    /**
     * Tests the comparison of a list of tasks with different priorities.
     */
    @Test
    public void sortTasksByPriority() {
        Task highPriorityTask = new ToDo("High Priority Task");
        highPriorityTask.setPriority(Priority.HIGH);

        Task mediumPriorityTask = new Deadline("Medium Priority Task", null);
        mediumPriorityTask.setPriority(Priority.MEDIUM);

        Task lowPriorityTask = new Event("Low Priority Task", null, null);
        lowPriorityTask.setPriority(Priority.LOW);

        List<Task> tasks = new ArrayList<>();
        tasks.add(mediumPriorityTask);
        tasks.add(lowPriorityTask);
        tasks.add(highPriorityTask);

        Collections.sort(tasks, priorityComparator);

        assertEquals(highPriorityTask, tasks.get(0), "High priority task should be at index 0");
        assertEquals(mediumPriorityTask, tasks.get(1), "Medium priority task should be at index 1");
        assertEquals(lowPriorityTask, tasks.get(2), "Low priority task should be at index 2");
    }
}
