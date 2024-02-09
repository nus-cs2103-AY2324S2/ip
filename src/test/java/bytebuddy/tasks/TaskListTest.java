package bytebuddy.tasks;

import static bytebuddy.constants.ExceptionErrorMessages.EMPTY_DESCRIPTION_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.NO_SUCH_TASK_NUMBER_ERROR_MESSAGE;
import static bytebuddy.constants.ExceptionErrorMessages.NUMBER_FORMAT_ERROR_MESSAGE;
import static bytebuddy.constants.Formats.DEADLINE_FORMAT;
import static bytebuddy.constants.Formats.EVENT_FORMAT;
import static bytebuddy.constants.Information.SOLID_LINE_BREAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bytebuddy.exceptions.ByteBuddyException;

public class TaskListTest {


    @Test
    public void testTodo() throws ByteBuddyException, IOException {
        TaskList taskList = new TaskList();
        String todoInfo = "Test";

        taskList.todo(todoInfo);

        assertEquals("[T][✕] Test", taskList.get(0).toString());
        assertEquals(1, taskList.size());
    }

    @Test
    public void testTodoEmptyDescription() {
        TaskList taskList = new TaskList();
        String emptyTodoInfo = "";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.todo(emptyTodoInfo));
        assertEquals("holup!! " + EMPTY_DESCRIPTION_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testDeadline() throws ByteBuddyException, IOException {
        TaskList taskList = new TaskList();
        String deadlineInfo = "Submit report /by 2023-02-28";

        taskList.deadline(deadlineInfo);

        assertEquals("[D][✕] Submit report (by: Feb 28 2023)", taskList.get(0).toString());
        assertEquals(1, taskList.size());
    }

    @Test
    public void testDeadlineIncorrectFormat() {
        TaskList taskList = new TaskList();
        String incorrectDeadlineInfo = "Submit report";

        ByteBuddyException exception =
                assertThrows(ByteBuddyException.class, () -> taskList.deadline(incorrectDeadlineInfo));
        assertEquals("holup!! The correct usage is: " + DEADLINE_FORMAT, exception.getMessage());
    }

    @Test
    public void testEvent() throws ByteBuddyException, IOException {
        TaskList taskList = new TaskList();
        String eventInfo = "TestEvent /from 2019-10-15 /to 2/12/2019 1800";

        taskList.event(eventInfo);

        assertEquals("[E][✕] TestEvent (from: Oct 15 2019 to: 2 of December 2019, 6PM)", taskList.get(0).toString());
        assertEquals(1, taskList.size());
    }

    @Test
    public void testEventIncorrectFormat() {
        TaskList taskList = new TaskList();
        String incorrectEventInfo = "Project meeting /from 2023-02-28 14:00";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.event(incorrectEventInfo));
        assertEquals("holup!! The correct usage is: " + EVENT_FORMAT, exception.getMessage());
    }

    @Test
    public void testMark() throws ByteBuddyException, IOException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test"));

        taskList.mark("1");

        assertEquals("[T][✓] Test", taskList.get(0).toString());
    }

    @Test
    public void testMarkInvalidFormat() {
        TaskList taskList = new TaskList();
        String invalidMarkInfo = "abc";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.mark(invalidMarkInfo));
        assertEquals("holup!! " + NUMBER_FORMAT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testMarkInvalidTaskNumber() {
        TaskList taskList = new TaskList();
        String invalidTaskNumber = "10";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.mark(invalidTaskNumber));
        assertEquals("holup!! " + NO_SUCH_TASK_NUMBER_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testUnmark() throws ByteBuddyException, IOException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test"));
        taskList.mark("1");

        taskList.unmark("1");

        assertEquals("[T][✕] Test", taskList.get(0).toString());
    }

    @Test
    public void testUnmarkInvalidFormat() {
        TaskList taskList = new TaskList();
        String invalidUnmarkInfo = "xyz";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.unmark(invalidUnmarkInfo));
        assertEquals("holup!! " + NUMBER_FORMAT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testUnmarkInvalidTaskNumber() {
        TaskList taskList = new TaskList();
        String invalidTaskNumber = "5";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.unmark(invalidTaskNumber));
        assertEquals("holup!! " + NO_SUCH_TASK_NUMBER_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testDelete() throws ByteBuddyException, IOException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test"));

        taskList.delete("1");

        assertEquals(0, taskList.size());
    }

    @Test
    public void testDeleteInvalidFormat() {
        TaskList taskList = new TaskList();
        String invalidDeleteInfo = "invalid";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.delete(invalidDeleteInfo));
        assertEquals("holup!! " + NUMBER_FORMAT_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testDeleteInvalidTaskNumber() {
        TaskList taskList = new TaskList();
        String invalidTaskNumber = "8";

        ByteBuddyException exception = assertThrows(ByteBuddyException.class, () -> taskList.delete(invalidTaskNumber));
        assertEquals("holup!! " + NO_SUCH_TASK_NUMBER_ERROR_MESSAGE, exception.getMessage());
    }

    @Test
    public void testPrintTaskList() throws ByteBuddyException {
        // Redirect System.out for testing print output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test"));
        taskList.add(new Deadline("Submit report", "2023-02-28"));

        taskList.printTaskList();

        String expectedOutput = "\t"
                + SOLID_LINE_BREAK
                + "\n\t 1.[T][✕] Test\n"
                + "\t 2.[D][✕] Submit report (by: Feb 28 2023)\n"
                + "\t"
                + SOLID_LINE_BREAK
                + "\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }
}
