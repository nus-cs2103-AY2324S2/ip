package grizzly.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.tasks.Todo;
import grizzly.utils.Database;
import grizzly.utils.Storage;

public class DeleteRecordCommandTest {

    private Storage storage;

    public DeleteRecordCommandTest() {
        try {
            storage = new Storage("data/help.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteRecordIndexOutOfBounds() {

        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "task 1");
        Command c = new DeleteRecordCommand(testMap);
        try {
            c.execute(db, storage);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), ("Error Deleting Record! Given index 1 out of bounds\n"
                                             + "taskList current at size: 0"));
        }
    }

    @Test
    public void testDeleteRecordInvalidIndex() {

        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();

        testMap.put("description", "task wewowewo");
        Command c = new DeleteRecordCommand(testMap);
        try {
            c.execute(db, storage);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), ("Error Deleting Record! Given index "
                                            + "wewowewo is not a valid index"));
        }
    }

    @Test
    public void testDeleteRecordInsufficientInfo() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "task");
        Command c = new DeleteRecordCommand(testMap);
        try {
            c.execute(db, storage);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), ("Error Deleting Record! Insufficient information for delete!"));
        }
    }

    @Test
    public void testDeleteRecordWithTasks() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "task 1");
        Command c = new DeleteRecordCommand(testMap);
        try {
            db.addTask(new Todo(false, "task 1"));
            db.addTask(new Todo(false, "task 2"));
            db.addTask(new Todo(false, "task 3"));
            String output = c.execute(db, storage);
            assertTrue(db.taskListSize() == 2);
            assertEquals(output, ("Task \"[T][ ] task 1\" has been removed!"));
        } catch (Exception e) {
            fail();
        }
    }





}
