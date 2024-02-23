package notduke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task testUnmarked = new Task("Test1", false);
    private Task testMarked = new Task("Test2", true);
    @Test
    public void testTaskInfo_unmarked() {
        assertEquals(testUnmarked.taskInfo(), "[ ] Test1");
    }
    @Test
    public void testTaskInfo_marked() {
        assertEquals(testMarked.taskInfo(), "[X] Test2");
    }
    @Test
    public void unmark_unmarked_exceptionThrown() {
        try {
            testUnmarked.unmark();
            fail();
        } catch (Exception e) {
            assertEquals(e.toString(), "Huh?! Task is already unmarked!");
        }
    }

    @Test
    public void mark_marked_exceptionThrown() {
        try {
            testMarked.mark();
            fail();
        } catch (Exception e) {
            assertEquals(e.toString(), "Huh?! Task is already marked!");
        }
    }

    @Test
    public void mark_unmarked_success() {
        try {
            assertEquals(testUnmarked.mark(), "Nice! I've marked this task as done:" + testUnmarked.taskInfo());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void unmark_marked_success() {
        try {
            assertEquals(testMarked.unmark(), "OK, I've marked this task as not done yet:" + testMarked.taskInfo());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSaveOutput() {
        assertEquals(testUnmarked.saveOutput(), "| 0 | Test1");
        assertEquals(testMarked.saveOutput(), "| 1 | Test2");
    }
}

