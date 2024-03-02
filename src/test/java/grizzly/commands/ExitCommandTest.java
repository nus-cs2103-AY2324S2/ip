package grizzly.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import grizzly.utils.Database;
import grizzly.utils.Storage;

public class ExitCommandTest {

    private Storage storage;

    public ExitCommandTest() {
        try {
            storage = new Storage("data/help.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testExitCommand() {
        Database db = new Database();

        ExitCommand e = new ExitCommand();
        try {
            assertEquals(e.execute(db, storage), ("0 tasks saved\n0 contacts saved\n"));
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testExitCommandWithoutStorage() {
        Database db = new Database();
        ExitCommand e = new ExitCommand();
        try {
            assertEquals(e.execute(db, null), ("Data not saved: Storage initialisation not sucessful"));
        } catch (Exception ex) {
            fail();
        }
    }
}
