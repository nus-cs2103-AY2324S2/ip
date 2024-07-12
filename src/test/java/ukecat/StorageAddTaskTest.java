package ukecat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

// Used ChatGPT to generate Javadoc for these unit tests.
public class StorageAddTaskTest {

    /**
     * Test the successful addition of a ToDo task.
     * <p>
     * Checks if the task is added successfully, and the task count is updated accordingly.
     */
    @Test
    public void addTask_validToDo_success() {
        // checks if task is added successfully
        Storage.setTaskCount(0);
        Storage.setInput("todo buy bread");
        Storage.setDesc("buy bread");
        Storage.setWords(new String[]{"todo", "buy bread"});

        assertEquals("  Task added: [T][ ] buy bread" + System.lineSeparator()
                + "  You have 1 task in the list now.", Storage.addTask());
    }

    /**
     * Test the successful addition of an Event task.
     * <p>
     * Adds an Event task and checks if the result indicates successful addition with the expected format.
     */
    @Test
    public void addTask_validEvent_success() {
        // Add a valid Event task
        Storage.setTaskCount(0);
        Storage.setInput("event Team meeting /from 2024-03-01 /to 2024-03-02");
        Storage.setWords(new String[]{"event", "Team meeting", "/from", "2024-03-01", "/to", "2024-03-02"});
        Storage.setDesc("Team meeting");
        Storage.setStart(LocalDate.parse("2024-03-01"));
        Storage.setEnd(LocalDate.parse("2024-03-02"));
        String result = Storage.addTask();

        assertEquals("  Task added: [E][ ] Team meeting (from: Mar 1 2024 to: Mar 2 2024)"
                + System.lineSeparator() + "  You have 1 task in the list now.", result);
    }

    /**
     * Test the successful addition of a Deadline task.
     * <p>
     * Adds a Deadline task and checks if the result indicates successful addition with the expected format.
     */
    @Test
    public void addTask_validDeadline_success() {
        // Add a valid Deadline task
        Storage.setTaskCount(0);
        Storage.setInput("deadline Submit report /by 2024-03-01");
        Storage.setWords(new String[]{"deadline", "Submit report", "/by", "2024-03-01"});
        Storage.setDesc("Submit report");
        Storage.setBy(LocalDate.parse("2024-03-01"));
        String result = Storage.addTask();

        assertEquals("  Task added: [D][ ] Submit report (by: Mar 1 2024)"
                + System.lineSeparator() + "  You have 1 task in the list now.", result);
    }

    /**
     * Test the successful addition of a RecurTask with daily recurrence.
     * <p>
     * Adds a RecurTask with daily recurrence and checks if the
     * result indicates successful addition with the expected format.
     */
    @Test
    public void addTask_validRecurTaskDaily_success() {
        // Add a valid RecurTask
        Storage.setTaskCount(0);
        Storage.setInput("recur Gym workout /every day");
        Storage.setWords(new String[]{"recur", "Gym workout", "/every", "day"});
        Storage.setDesc("Gym workout");
        Storage.setRecurType("day");
        String result = Storage.addTask();

        assertEquals("  Task added: [RD][ ] Gym workout (Due today!)"
                + System.lineSeparator() + "  You have 1 task in the list now.", result);
    }

    /**
     * Test the successful addition of a RecurTask with weekly recurrence.
     * <p>
     * Adds a RecurTask with weekly recurrence and checks if the
     * result indicates successful addition with the expected format.
     */
    @Test
    public void addTask_validRecurTaskWeekly_success() {
        // Add a valid RecurTask with weekly recurrence
        Storage.setTaskCount(0);
        Storage.setInput("recur Weekly meeting /every week");
        Storage.setWords(new String[]{"recur", "Weekly meeting", "/every", "week"});
        Storage.setDesc("Weekly meeting");
        Storage.setRecurType("week");
        String result = Storage.addTask();

        assertEquals("  Task added: [RW][ ] Weekly meeting (Due in 6 days!)"
                + System.lineSeparator() + "  You have 1 task in the list now.", result);
    }

    /**
     * Test the successful addition of a RecurTask with monthly recurrence.
     * <p>
     * Adds a RecurTask with monthly recurrence and checks if the
     * result indicates successful addition with the expected format.
     */
    @Test
    public void addTask_validRecurTaskMonthly_success() {
        // Add a valid RecurTask with monthly recurrence
        Storage.setTaskCount(0);
        Storage.setInput("recur Monthly report /every month");
        Storage.setWords(new String[]{"recur", "Monthly report", "/every", "month"});
        Storage.setDesc("Monthly report");
        Storage.setRecurType("month");
        String result = Storage.addTask();

        assertEquals("  Task added: [RM][ ] Monthly report (Due in 28 days!)"
                + System.lineSeparator() + "  You have 1 task in the list now.", result);
    }

    /**
     * Test exception handling for invalid input format.
     * <p>
     * Throws an exception if the input format is incorrect and checks if the correct error message is returned.
     */
    @Test
    public void addTask_invalidInput_exceptionThrown() {
        // throws exception if input not correct format
        Storage.setTaskCount(0);
        Storage.setInput("buy bread");
        Storage.setDesc("buy bread");
        Storage.setWords(new String[]{"todo", "buy bread"});
        assertEquals("Error adding task: Wrong format, use: todo <desc>", Storage.addTask());
    }
}
