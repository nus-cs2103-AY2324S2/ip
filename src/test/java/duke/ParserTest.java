package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void deleteTest() {
        Duke duke = new Duke();
        TaskList testList = new TaskList(duke);
        TaskList testList2 = new TaskList(duke);
        String[] words = new String[] {"todo", "test", "123"};
        String[] words2 = new String[] {"todo", "test", "456"};
        String[] words3 = new String[] {"todo", "test", "789"};
        testList.addToDo(words, true, false);
        testList.addToDo(words2, true, false);
        testList.addToDo(words3, true, false);
        testList2.addToDo(words, true, false);
        testList2.addToDo(words3, true, false);
        Parser.commands(testList, "delete 2", false, false, duke);
        String expected = testList.list();
        String actual = testList2.list();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTest2() {
        Duke duke = new Duke();
        TaskList testList = new TaskList(duke);
        TaskList testList2 = new TaskList(duke);
        String[] words = new String[] {"todo", "test", "123"};
        String[] words2 = new String[] {"todo", "test", "456"};
        String[] words3 = new String[] {"todo", "test", "789"};
        testList.addToDo(words, true, false);
        testList.addToDo(words2, true, false);
        testList.addToDo(words3, true, false);
        testList2.addToDo(words, true, false);
        testList2.addToDo(words2, true, false);
        testList2.addToDo(words3, true, false);
        Parser.commands(testList, "delete 4", false, false, duke);
        String expected = testList.list();
        String actual = testList2.list();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTest3() {
        Duke duke = new Duke();
        TaskList testList = new TaskList(duke);
        TaskList testList2 = new TaskList(duke);
        String[] words = new String[] {"todo", "test", "123"};
        String[] words2 = new String[] {"todo", "test", "456"};
        String[] words3 = new String[] {"todo", "test", "789"};
        testList.addToDo(words, true, false);
        testList.addToDo(words2, true, false);
        testList.addToDo(words3, true, false);
        testList2.addToDo(words, true, false);
        testList2.addToDo(words2, true, false);
        Parser.commands(testList, "delete 3", false, false, duke);
        String expected = testList.list();
        String actual = testList2.list();
        assertEquals(expected, actual);
    }
}
