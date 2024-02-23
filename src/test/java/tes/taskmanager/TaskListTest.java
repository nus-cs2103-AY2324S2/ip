package tes.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import tes.command.DateAndTimeFormatterException;


/**
 * Represents the testing class for the TaskList class.
 */
public class TaskListTest {
    /**
     * Tests the storeDeadline method in the TaskList class.
     */
    @Test
    public void testStoreDeadlineWithValidInput() {
        String taskDescription = "return book";
        String by = "2022-12-12 1900";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskListStub taskList = new TaskListStub(tasks);
        String actual = taskList.storeDeadline(taskDescription, by);
        assertEquals("D |   | return book | by: Dec 12 2022 07:00 PM", actual);
    }

    @Test
    public void testStoreDeadlineWithInvalidDateAndTime() {
        String taskDescription = "return book";
        String by = "2022-12-12 7000";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskListStub taskList = new TaskListStub(tasks);
        String actual = taskList.storeDeadline(taskDescription, by);
        assertEquals("Wrong format of Date or Time!!!! Here's a quick tip:\n"
                + "O p e n  Y o u r  B l u r r y  E y e s  W h e n  T y p i n g ! ! !", actual);
    }

    @Test
    public void testStoreEventWithValidInput() {
        String taskDescription = "gathering";
        String from = "2022-12-12 1900";
        String to = "2022-12-12 2300";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskListStub taskList = new TaskListStub(tasks);
        String actual = taskList.storeEvent(taskDescription, from, to);
        assertEquals("E |   | gathering | from: Dec 12 2022 07:00 PM to: Dec 12 2022 11:00 PM", actual);
    }
    @Test
    public void testStoreEventWithInvalidDateAndTime() {
        String taskDescription = "gathering";
        String from = "2022-12-1 1800";
        String to = "2022-12-02 0000";
        ArrayList<Task> tasks = new ArrayList<>();
        TaskListStub taskList = new TaskListStub(tasks);
        String actual = taskList.storeEvent(taskDescription, from, to);
        assertEquals("Wrong format of Date or Time!!!! Here's a quick tip:\n"
                + "O p e n  Y o u r  B l u r r y  E y e s  W h e n  T y p i n g ! ! !", actual);
    }

}
