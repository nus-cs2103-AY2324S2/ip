package grizzly.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.tasks.Todo;
import grizzly.utils.Database;
import grizzly.utils.Storage;

public class FindCommandTest {

    private Storage storage;

    public FindCommandTest() {
        try {
            storage = new Storage("data/help.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFindCommandMissingDescripton() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "");
        FindCommand command = new FindCommand(testMap);
        try {
            command.execute(db, storage);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), ("Missing information: description"));
        }
    }

    @Test
    public void testFindCommand() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        db.addTask(new Todo(false, "task 1"));
        testMap.put("description", "task 1");
        FindCommand command = new FindCommand(testMap);
        try {
            String output = command.execute(db, storage);
            assertEquals(output, ("----------\nTasks\n----------\n1.[T][ ] task 1\n"
                                     + "No contacts found\n"));
        } catch (Exception e) {
            fail();
        }
    }
}
