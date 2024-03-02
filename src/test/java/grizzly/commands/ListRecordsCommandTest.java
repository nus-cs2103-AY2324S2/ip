package grizzly.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.utils.Database;
import grizzly.utils.Storage;

public class ListRecordsCommandTest {
    private Storage storage;

    public ListRecordsCommandTest() {
        try {
            storage = new Storage("data/help.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testListTaskCommand() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "tasks");
        ListRecordsCommand command = new ListRecordsCommand(testMap);
        try {
            String output = command.execute(db, storage);
            assertEquals(output, ("----------\nTasks\n----------\n"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testListContactCommand() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "contacts");
        ListRecordsCommand command = new ListRecordsCommand(testMap);
        try {
            String output = command.execute(db, storage);
            assertEquals(output, ("----------\nContacts\n----------\n"));
        } catch (Exception e) {
            fail();
        }
    }
}
