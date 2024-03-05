package quacky;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void markTask_outOfBounds_throwsQuackyException() {
        try {
            TaskList tasks = new TaskList();
            tasks.markCompleteTask(50);
            fail(); // the test should not reach this line
        } catch (QuackyException e) {
            String expectedErrorMessage = "Quack. The task is not found";
            String actualErrorMessage = e.getMessage();
            assertEquals(expectedErrorMessage,actualErrorMessage);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void findTaskByKeyword_success() {
        try {
            TaskList tasks = new TaskList();
            Task task1 = new Todo("CS2103T Assignment");
            tasks.addTask(task1);
            Task task2 = new Todo("CS2109S Assignment");
            tasks.addTask(task2);
            Task task3 = new Todo("Write blog");
            tasks.addTask(task3);
            String actualOutput = tasks.findTasksByKeyword("Assignment").toString();
            TaskList anotherTaskList = new TaskList();
            anotherTaskList.addTask(task1);
            anotherTaskList.addTask(task2);
            String expectedOutput = anotherTaskList.toString();
            assertEquals(expectedOutput,actualOutput);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addTasks_clashingTimes_throwsQuackyException() {
        try {
            TaskList tasks = new TaskList();
            Deadline deadline1 = new Deadline("Finish editing essay", LocalDate.parse("2024-01-01"));
            tasks.addTask(deadline1);
            Deadline deadline2 = new Deadline("Finish writing script", LocalDate.parse("2024-01-01"));
            tasks.addTask(deadline2);
        } catch (QuackyException e) {
            String expectedErrorMessage = "Task clashes with existing task(s)";
            String actualErrorMessage = e.getMessage();
            assertEquals(expectedErrorMessage,actualErrorMessage);
        } catch (Exception e) {
            fail();
        }
    }

}