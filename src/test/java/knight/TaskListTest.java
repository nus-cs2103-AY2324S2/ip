package knight;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    class DummyTask extends Task {
        DummyTask(String name) {
            super(name);
        }

        @Override
        String getCommand() {
            return "lorem ipsum " + name;
        }

        @Override
        public String toString() {
            return "anima mea " + name;
        }

        @Override
        void update(String updateMessage) {
            return;
        }
    }
    @Test
    public void toStringTest1() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new DummyTask("gloria"));
        tasks.add(new DummyTask("in excelsis deo"));
        TaskList taskList = new TaskList(tasks);
        assertEquals("1. anima mea gloria\n2. anima mea in excelsis deo", taskList.toString());
    }

    @Test
    public void getCommandsTest1() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new DummyTask("gloria"));
        tasks.add(new DummyTask("in excelsis deo"));
        TaskList taskList = new TaskList(tasks);
        assertEquals("lorem ipsum gloria\nlorem ipsum in excelsis deo", taskList.getCommands());
    }
}
