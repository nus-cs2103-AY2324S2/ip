package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void listTest() {
        TaskList testList = new TaskList(null);
        String[] words = new String[] {"todo", "test", "123"};
        testList.addToDo(words, true, false);
        String actual = testList.list();
        String expected = "1: " + testList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }


}
