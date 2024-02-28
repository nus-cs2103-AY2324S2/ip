package dukio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the `State` class.
 */
public class StateTest {

    @Test
    public void getTask_outOfBounds_returnsNull() {
        State s = new State();
        Task t = s.getTask(-1);
        Assertions.assertNull(t);
        Task t1 = s.getTask(1);
        Assertions.assertNull(t1);
    }

    @Test
    public void addTask_works() {
        State s = new State();
        Task t = new Task("", false);
        s.addTask(t);
        Task foundTask = s.getTasks().get(0);
        Assertions.assertEquals(t, foundTask);
    }

    @Test
    public void getTask_inBounds_returnsTask() {
        State s = new State();
        Task t = new Task("", false);
        s.addTask(t);
        Task t1 = s.getTask(0);
        Assertions.assertEquals(t, t1);
    }

}
