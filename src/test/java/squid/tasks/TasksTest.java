package squid.tasks;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import squid.exceptions.DuplicateTaskNameException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TasksTest {

    // Class to be tested
    private Tasks tasks;

    // Dependencies to be stubbed
    private TodoStub todo;


    @Before
    public void setup() {
         tasks = new Tasks();
         todo = new TodoStub();
    }


    class TodoStub implements TaskInterface {

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
            return null;
        }
    }

    @Test
    public void testInitialization() {
        Tasks tasks = new Tasks();
        assertEquals(0, Tasks.size());
    }
    @Test
    public void testAdd() {
        try {
            Tasks tasks = new Tasks();
            Task task = new Todo("test");
            Tasks.add(task);
            Assertions.assertEquals(Tasks.get(0).task, task.task);
        } catch (DuplicateTaskNameException e) {
            fail();
        }
    }


}
