package paimon.task;

import paimon.exception.ChatBotParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void markTaskAsDone_normal_success() throws ChatBotParameterException {
        TaskList taskListToBeTested = new TaskList();
        taskListToBeTested.addEvent("hello", "tmr", "weekend", true);
        taskListToBeTested.addDeadline("deadline", "5/9/2002 1600", false);
        taskListToBeTested.addToDo("1", false);
        assertTrue(taskListToBeTested.markTaskAsDone("2").isDone());
    }

    @Test
    void markTaskAsDone_markTrueAlready_exceptionThrown() {
        try {
            TaskList taskListToBeTested = new TaskList();
            taskListToBeTested.addEvent("hello", "tmr", "weekend", true);
            taskListToBeTested.addDeadline("deadline", "5/9/2002 1600", true);
            taskListToBeTested.addToDo("1", false);
            assertTrue(taskListToBeTested.markTaskAsDone("1").isDone());
        } catch (ChatBotParameterException e) {
            assertEquals(e.getMessage(), "This task is already marked done!");
        }
    }

    @Test
    void markTaskAsDone_taskOutOfBound_exceptionThrown() {
        try {
            TaskList taskListToBeTested = new TaskList();
            taskListToBeTested.addEvent("hello", "tmr", "weekend", true);
            taskListToBeTested.addDeadline("deadline", "5/9/2002 1600", true);
            taskListToBeTested.addToDo("1", false);
            assertTrue(taskListToBeTested.markTaskAsDone("4").isDone());
        } catch (ChatBotParameterException e) {
            assertEquals(e.getMessage(), "The task does not exists in the task list.");
        }
    }
}