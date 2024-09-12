package chrisPBacon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.InvalidTaskNameException;
import task.TaskList;
import util.Storage;
import util.Ui;

public class UiTest {
    @Test
    public void printTodo_invalidTaskNameExceptionThrown() {
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui(storage);
        String errorMsg = "Ooink oink! What's the name of your task?\n"
                + " >> todo ...\n";
        String userInput = "todo ";

        try {
            TaskList taskList = new TaskList();
            ui.printTodo(userInput, taskList);
            assertEquals(0, taskList.getSize());
            fail(); // the test should not reach this line
        } catch (InvalidTaskNameException e) {
            assertEquals(errorMsg, e.getMessage());
        }
    }

    @Test
    public void printDeadline_invalidTaskNameExceptionThrown() {
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui(storage);
        String errorMsg = "Ooink oink! Please describe your deadline >.<\n"
                + " >> deadline ... /by dd/MM/yyyy\n";
        String userInput = "deadline read book /by";

        try {
            TaskList taskList = new TaskList();
            ui.printDeadline(userInput, taskList);
            assertEquals(0, taskList.getSize());
            fail(); // the test should not reach this line
        } catch (InvalidTaskNameException e) {
            assertEquals(errorMsg, e.getMessage());
        }
    }

    @Test
    public void printDeadline_dateTimeParseExceptionThrown() {
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui(storage);
        String userInput = "deadline read book /by 22-10-2024";

        try {
            TaskList taskList = new TaskList();
            ui.printDeadline(userInput, taskList);
            assertEquals(0, taskList.getSize()); // Exception is handled.
        } catch (InvalidTaskNameException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void printEvent_invalidTaskNameExceptionThrown() {
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui(storage);
        String errorMsg = "Ooink oink! Please describe your event >.<\n"
                + " >> event ... /from ... /to ...\n";
        String userInput = "event read book /from tdy /to";

        try {
            TaskList taskList = new TaskList();
            ui.printEvent(userInput, taskList);
            assertEquals(0, taskList.getSize());
            fail(); // the test should not reach this line
        } catch (InvalidTaskNameException e) {
            assertEquals(errorMsg, e.getMessage());
        }
    }
}
