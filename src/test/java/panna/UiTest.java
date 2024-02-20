package panna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    private Ui ui = new Ui();
    @Test
    public void markTest() throws PannaException {
        TaskList tasks = new TaskList();
        Task t = new Task("Test");
        tasks.add(t);
        ui.mark(tasks, 1);
        boolean actual = tasks.get(0).getDone();
        boolean expected = true;

        assertEquals(actual, expected);


    }

    @Test
    public void unmarkTest() throws PannaException {
        TaskList tasks = new TaskList();
        Task t = new Task("Test");
        tasks.add(t);
        ui.mark(tasks, 1);
        tasks.add(new Task("test2"));
        ui.mark(tasks, 2);
        ui.unmark(tasks, 1);
        boolean actual = tasks.get(0).getDone();
        boolean expected = false;

        assertEquals(actual, expected);


    }

}
