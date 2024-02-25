package chatterpal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests the functionality of the Storage class, ensuring tasks are correctly added, listed,
 * and found, and that the schedule viewing functionality accurately reflects tasks for specific dates.
 */
public class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
    }

    @Test
    public void testAddTask() {
        ToDo task = new ToDo("Read book");
        storage.add(task);
        assertEquals(1, storage.size(), "Storage should have 1 task after adding.");
    }

    @Test
    public void testAddToListOutput() {
        Task task = new ToDo("Read book");
        storage.add(task);
        String expectedOutput = "Got it. I've added this task:\n  [T][ ] Read book\nNow you have 1 tasks in the list.";
        assertEquals(expectedOutput, storage.addToListOutput(task), "Output does not match expected string after adding task.");
    }

    @Test
    public void testPrintList() {
        ToDo task1 = new ToDo("Read book");
        storage.add(task1);
        String expectedList = "1. [T][ ] Read book\n";
        assertEquals(expectedList, storage.printList(), "List output does not match expected string.");
    }

    @Test
    public void testFindNoMatch() {
        storage.add(new ToDo("Read book"));
        String findResult = storage.find("exercise");
        assertEquals("Nothing was found", findResult, "Find should return 'Nothing was found' when no tasks match.");
    }

    @Test
    public void testFindMatch() {
        storage.add(new ToDo("Read book"));
        storage.add(new ToDo("Exercise"));
        String expectedOutput = "2. [T][ ] Exercise\n";
        assertEquals(expectedOutput, storage.find("Exercise"), "Find should return matching tasks.");
    }

    @Test
    public void testViewScheduleForDeadline() {
        LocalDate today = LocalDate.now();
        LocalDateTime deadlineDateTime = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 23, 59);
        Deadline deadlineTask = new Deadline("Submit assignment", deadlineDateTime);
        storage.add(deadlineTask);
        String expectedOutput = "Submit assignment\n";
        assertEquals(expectedOutput, storage.viewSchedule(today), "View schedule should list tasks due on the specified date.");
    }

    @Test
    public void testViewScheduleForEvent() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(1);
        LocalDate endDate = today.plusDays(1);
        Event eventTask = new Event("Team meeting", startDate.atStartOfDay(), endDate.atTime(23, 59));
        storage.add(eventTask);

        String expectedOutput = "Team meeting\n";
        assertEquals(expectedOutput, storage.viewSchedule(today), "View schedule should list events happening on the specified date.");
    }
}