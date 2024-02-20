package toothless.commands;

import org.junit.jupiter.api.Test;
import toothless.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MarkCommandTest {
    @Test
    public void markCorrectTask() {
        try {
            Ui ui = new UiStub1();
            TaskList taskList = new TaskListStub1();
            Storage storage = new Storage("./data/test/toothless.txt");
            MarkCommand markCommand = new MarkCommand("1");
            markCommand.handle(ui, taskList, storage);
            assertEquals("X", taskList.getTask(0).getStatusIcon());
        } catch (ToothlessException e) {
            fail();
        }
    }

    @Test
    public void markIncorrectTask1() {
        try {
            Ui ui = new UiStub1();
            TaskList taskList = new TaskListStub1();
            Storage storage = new Storage("./data/test/toothless.txt");
            MarkCommand markCommand = new MarkCommand("2");
            markCommand.handle(ui, taskList, storage);
            fail();
        } catch (ToothlessException e) {
            assertEquals(e.getMessage(), "Human trying to mark nothing ^O^. Foolish");
        }
    }

    @Test
    public void markIncorrectTask2() {
        try {
            Ui ui = new UiStub1();
            TaskList taskList = new TaskListStub1();
            Storage storage = new Storage("./data/test/toothless.txt");
            MarkCommand markCommand = new MarkCommand("-1");
            markCommand.handle(ui, taskList, storage);
            fail();
        } catch (ToothlessException e) {
            assertEquals(e.getMessage(), "Human trying to mark nothing ^O^. Foolish");
        }
    }

    @Test
    public void markIncorrectTask3() {
        try {
            Ui ui = new UiStub1();
            TaskList taskList = new TaskListStub1();
            Storage storage = new Storage("./data/test/toothless.txt");
            MarkCommand markCommand = new MarkCommand("hamburger");
            markCommand.handle(ui, taskList, storage);
            fail();
        } catch (ToothlessException e) {
            assertEquals(e.getMessage(), "Number put is not number.\nPlease put real number ._.");
        }
    }
}
