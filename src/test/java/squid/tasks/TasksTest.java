package squid.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import squid.constants.Regex;
import squid.exceptions.DuplicateTaskNameException;
import squid.exceptions.IncorrectIndexException;
import squid.exceptions.ParseFailException;
import squid.exceptions.SquidDateException;

public class TasksTest {

    // Class to be tested
    private Tasks tasks;

    // Dependencies to be stubbed
    private TodoStub stub;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    class TodoStub extends Task implements TaskInterface {

        public TodoStub(String task) {
            super(task);
        }

        @Override
        public void setCompleted(boolean completed) {

        }

        @Override
        public boolean isCompleted() {
            return false;
        }

        @Override
        public String completedIcon() {
            return null;
        }

        @Override
        public String getType() {
            return null;
        }

        @Override
        public String getAdditionalInfo() {
            return null;
        }

        @Override
        public String parseStr() {
            return "before" + Regex.TASK_SPLIT + "middle" + Regex.TASK_SPLIT + "after";
        }

        @Override
        public String toString() {
            return "test";
        }
    }


    @BeforeEach
    public void setup() {
        tasks = new Tasks();
        stub = new TodoStub("test");
        System.setOut(new PrintStream(outContent));
    }




    @Test
    public void testInitialization() {
        assertEquals(0, Tasks.size());
    }

    @Test
    public void testAdd() {
        try {
            Tasks.add(stub);
            assertEquals(1, Tasks.size());
        } catch (DuplicateTaskNameException e) {
            fail();
        }
    }

    @Test
    public void testAdd_addDuplicate_exceptionThrown() {
        assertThrows(DuplicateTaskNameException.class, () -> {
            Tasks.add(stub);
            Tasks.add(stub);
        });
    }

    @Test
    public void testGet() {
        try {
            Tasks.add(stub);
            assertEquals(Tasks.get(0), stub);
        } catch (DuplicateTaskNameException | IncorrectIndexException e) {
            fail();
        }
    }

    @Test
    public void testGet_invalidIndex_exceptionThrown() {
        assertThrows(IncorrectIndexException.class, () -> {
            Tasks.get(1);
        });
    }

    @Test
    public void testGet_invalidIndex_exceptionThrown1() {
        assertThrows(IncorrectIndexException.class, () -> {
            Tasks.get(-1);
        });
    }

    @Test
    public void testList() {
        try {
            Tasks.add(stub);
            Tasks.list();
            assertEquals("1: test" + "\r" + "\n", Tasks.list());
        } catch (IncorrectIndexException | DuplicateTaskNameException e) {
            fail();
        }
    }

    @Test
    public void testParseTask_taskName() {
        String expected = "after";
        try {
            Task actual = Tasks.parseTask(stub.parseStr());

            assertEquals(expected, actual.getTaskName());
        } catch (ParseFailException | SquidDateException e) {
            fail();
        }
    }

    @Test
    public void testParseTask_taskCompleted() {
        boolean expected = false;
        try {
            Task actual = Tasks.parseTask(stub.parseStr());

            assertEquals(expected, actual.isCompleted());
        } catch (ParseFailException | SquidDateException e) {
            fail();
        }
    }


}
