package yapchit;

import yapchit.yapchitbackend.Handler;
import yapchit.yapchitbackend.Parser;
import yapchit.yapchitbackend.TaskList;
import yapchit.yapchitbackend.Ui;
import yapchit.yapchitbackend.tasks.Deadline;
import yapchit.yapchitexceptions.InvalidDetailException;
import yapchit.yapchitexceptions.YapchitException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test Handler class
 */
public class HandlerTest {

    private Handler handler;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new HandlerTest instance.
     */
    public HandlerTest() {
        handler = new Handler();
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Tests success condition of handleDeadline method of the Handler class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testHandleDeadlineSuccess() throws YapchitException {
        String input = "deadline assignment /by 2023-02-01";
        handler.handleDeadline(input, true, tasks, ui, parser);
        Deadline t = new Deadline("assignment", parser.parseTimestamp("2023-02-01"));
        assertEquals(t.getName(), tasks.getItem(tasks.getListSize() - 1).getName());
        assertEquals(t.getBy(), ((Deadline) tasks.getItem(tasks.getListSize() - 1)).getBy());

        input = "deadline test                          /by 2023-02-28";
        handler.handleDeadline(input, true, tasks, ui, parser);
        t = new Deadline("test", parser.parseTimestamp("2023-02-28"));
        assertEquals(t.getName(), tasks.getItem(tasks.getListSize() - 1).getName());
        assertEquals(t.getBy(), ((Deadline) tasks.getItem(tasks.getListSize() - 1)).getBy());

        input = "deadline test/by 2023-02-28";
        handler.handleDeadline(input, true, tasks, ui, parser);
        t = new Deadline("test", parser.parseTimestamp("2023-02-28"));
        assertEquals(t.getName(), tasks.getItem(tasks.getListSize() - 1).getName());
        assertEquals(t.getBy(), ((Deadline) tasks.getItem(tasks.getListSize() - 1)).getBy());
    }

    /**
     * Tests missing description fail condition of handleDeadline method of the Handler class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testHandleDeadlineMissingDescription() {
        String input = "deadline /by 2023-02-28";
        assertThrows(InvalidDetailException.class, () -> {
            handler.handleDeadline(input, true, tasks, ui, parser);
        });
    }

    /**
     * Tests invalid date format fail condition of handleDeadline method of the Handler class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testHandleDeadlineInvalidDateFormat() {
        String input = "deadline test /by 2023-2-28";
        assertThrows(InvalidDetailException.class, () -> {
            handler.handleDeadline(input, true, tasks, ui, parser);
        });
    }

    /**
     * Tests missing by param fail condition of handleDeadline method of the Handler class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testHandleDeadlineIMissingByParam() {
        String input = "deadline test /by";
        assertThrows(InvalidDetailException.class, () -> {
            handler.handleDeadline(input, true, tasks, ui, parser);
        });
    }

    /**
     * Tests missing desc and by param fail condition of handleDeadline method of the Handler class.
     *
     * @throws YapchitException if test fails.
     */
    @Test
    public void testHandleDeadlineIMissingDescAndByParam() {
        String input = "deadline /by";
        assertThrows(InvalidDetailException.class, () -> {
            handler.handleDeadline(input, true, tasks, ui, parser);
        });
    }

}


