package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Parser;
import duke.DukeException;
import duke.UI;
import duke.command.Command;
import duke.command.AddCommand;
import duke.task.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    private TaskList taskList;
    private Storage storage;
    private UI ui;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        try {
            ui = new UI();
            taskList = new TaskList();
            storage = new Storage("./test_duke.txt");
            storage.Store(taskList.toString());
        } catch (DukeException de)  {

        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        taskList = null;
        storage = null;
    }

    @Test
    public void add_todo_test() throws Exception {
        String input = "todo read book";
        Command cmd = Parser.parse(input);
        assertEquals("duke.command.AddCommand", cmd.getClass().getTypeName());
        System.out.println("add test");
        String expectedOutput = "Got it. I've added this task:\n[T][ ] read book\nNow you have 1 tasks in the list";
        cmd.execute(taskList, ui, storage);
        assertEquals(expectedOutput, ui.getCommandOutput());
    }

    @Test
    public void add_todo_test_fail() throws Exception {
        String input = "todo";
        try {
            Command cmd = Parser.parse(input);
            assertEquals("duke.command.AddCommand", cmd.getClass().getTypeName());
        } catch (DukeException de) {
            assertEquals("OMG! Description is empty. Cannot accept.", de.getMessage());
        }
    }
}