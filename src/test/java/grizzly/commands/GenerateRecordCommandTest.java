package grizzly.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.commands.GenerateRecordCommand.RecordType;
import grizzly.utils.Database;
import grizzly.utils.Storage;

public class GenerateRecordCommandTest {

    private Storage storage;

    public GenerateRecordCommandTest() {
        try {
            storage = new Storage("data/help.txt");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGenerateRecordCommandTodo() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "task 1");
        GenerateRecordCommand command = new GenerateRecordCommand(RecordType.TODO, testMap);
        try {
            String output = command.execute(db, storage);
            assertEquals(output, ("Todo Task added!\n" + "[T][ ] task 1" + "\n"
                                     + "You now have 1 tasks in the list."));
            assertEquals(db.taskListSize(), 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGenerateRecordCommandDeadline() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "task 2");
        testMap.put("by", "12/11/2024, 13:00");
        GenerateRecordCommand command = new GenerateRecordCommand(RecordType.DEADLINE, testMap);
        try {
            String output = command.execute(db, storage);
            assertEquals(output, ("Deadline Task added!\n" + "[D][ ] task 2\n"
                                     + "(by: 12 November 2024, 01:00PM)\n"
                                     + "You now have 1 tasks in the list."));
            assertEquals(db.taskListSize(), 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGenerateRecordCommandEvent() {
        Database db = new Database();
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("description", "task 3");
        testMap.put("from", "12/11/2024, 13:00");
        testMap.put("to", "12/11/2024, 14:00");
        GenerateRecordCommand command = new GenerateRecordCommand(RecordType.EVENT, testMap);
        try {
            String output = command.execute(db, storage);
            assertEquals(output, ("Event Task added!\n" + "[E][ ] task 3\n"
                                     + "(from: 12 November 2024, 01:00PM)\n"
                                     + "(to: 12 November 2024, 02:00PM)\n"
                                     + "You now have 1 tasks in the list."));
            assertEquals(db.taskListSize(), 1);
        } catch (Exception e) {
            fail();
        }
    }
}
