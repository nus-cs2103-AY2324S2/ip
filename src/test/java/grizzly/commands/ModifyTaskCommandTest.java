package grizzly.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.commands.ModifyTaskCommand.ModificationTypes;
import grizzly.tasks.Todo;
import grizzly.utils.Database;
import grizzly.utils.Storage;

public class ModifyTaskCommandTest {
    private Storage storage;

    public ModifyTaskCommandTest() {
        try {
            storage = new Storage("data/help.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testModifyTaskCommand() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "1");
        db.addTask(new Todo(false, "task 1"));

        Command c = new ModifyTaskCommand(ModificationTypes.MARK, testMap);
        try {
            String output = c.execute(db, storage);
            assertEquals(output, ("Good job on finishing your task!:\n[T][X] task 1"));
        } catch (Exception e) {
            fail();
        }
    }
}
