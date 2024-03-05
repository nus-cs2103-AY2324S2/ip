package yippee.commands;

import org.junit.jupiter.api.Test;
import yippee.Storage;
import yippee.TaskList;
import yippee.Ui;
import yippee.exceptions.InvalidCommandException;
import yippee.exceptions.YippeeException;
import yippee.tasks.Deadline;
import yippee.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    @Test
    public void testFindCommand() {
        TaskList testList = new TaskList();
        try {
            testList.addNewTask(new ToDo("a"));
            testList.addNewTask(new ToDo("ab"));
            testList.addNewTask(new ToDo("b"));
            testList.addNewTask(new Deadline("abc", "2022-12-12"));
            testList.addNewTask(new Deadline("bbb", "2022-12-02"));
        } catch(YippeeException e) {
            System.err.println(e.getMessage());
        }
        FindCommand testCommand = new FindCommand("a");
        String result = "";
        try {
            result = testCommand.execute(testList, new Ui(), new Storage("storageTest.txt"));
        } catch (InvalidCommandException e) {
            System.err.println(e.getMessage());
        }

        assertEquals("1. [T] [ ] a\n" +
                "2. [T] [ ] ab\n" +
                "3. [D] [ ] abc (by: Dec 12 2022)\n",
                result);
    }
}
