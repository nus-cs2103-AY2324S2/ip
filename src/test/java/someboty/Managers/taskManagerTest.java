package someboty.Managers;

import org.junit.jupiter.api.Test;

import someboty.exceptions.InputException;
import someboty.managers.FileManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    private taskManager manager;

    public TaskManagerTest() {
    }
    
    @Test
    public void DummyTest() {
        System.out.println("HELLO! IS THIS WORKING???");
        assertEquals(10,10);
    }

    @Test
    public void ToDoTest() {
        manager = new taskManager(new FileManager("taskManagerTestDummy"));

        String actual1 = manager.addTask('T', "Lord of the Bing Chillings").toString();
        String expected1 = "[T][ ] Lord of the Bing Chillings";
        assertEquals(expected1, actual1);

        String actual2 = manager.addTask('T', "  With trail and lead spaces   ").toString();
        String expected2 = "[T][ ] With trail and lead spaces";
        assertEquals(expected2, actual2);
    }

    @Test
    public void DeadlineTest() {
        manager = new taskManager(new FileManager("taskManagerTestDummy"));

        String actual1;
        String expected1 = "[D][ ] 1st Test (by: [15 Mar 2024 06:10])";
        try {
            actual1 = manager.addTask('D', "1st Test /by 03-15-2024 0610").toString();
        } catch (InputException e) {
            actual1 = e.getMessage();
        }
        assertEquals(expected1, actual1);

        String actual2;
        String expected2 = "[D][ ] 2nd Test (by: [15 Mar 2024 23:59])";
        try {
            actual2 = manager.addTask('D', "2nd Test /by 15-03-2024").toString();
        } catch (InputException e) {
            actual2 = e.getMessage();
        }
        assertEquals(expected2, actual2);

        String actual3;
        String expected3 = "Unable to identify the deadline date. Make sure to follow the format:\n"
                        + "'deadline DESCRIPTION /by VALID_DATE_FORMAT'";
        try {
            actual3 = manager.addTask('D', "Lord of the Bing Chillings /by12-12-2001").toString();
        } catch (InputException e) {
            actual3 = e.getMessage();
        }
        assertEquals(expected3, actual3);
    }

    @Test
    public void EventTest() {
        manager = new taskManager(new FileManager("taskManagerTestDummy"));

        String actual1;
        String expected1 = "[E][ ] 1st Test (from: [15 Mar 2024 06:10], to: [16 Oct 2025 09:50])";
        try {
            actual1 = manager.addTask('E', "1st Test /from 03-15-2024 0610 /to 16-10-2025 0950").toString();
        } catch (InputException e) {
            actual1 = e.getMessage();
        }
        assertEquals(expected1, actual1);

        String actual2;
        String expected2 = "[E][ ] 2nd Test (from: [13 Dec 2024 06:10], to: [13 Dec 2025 09:50])";
        try {
            actual2 = manager.addTask('E', "2nd Test /to 12-13-2025 0950 /from 13-12-2024 0610").toString();
        } catch (InputException e) {
            actual2 = e.getMessage();
        }
        assertEquals(expected2, actual2);

        String actual3;
        String expected3 = "Unable to identify the start and/or end date. Make sure to follow the format:\n"
                        + "event DESCRIPTION /from START /to END";
        try {
            actual3 = manager.addTask('E', "Lord of the Bing Chillings /from 12-12-2001").toString();
        } catch (InputException e) {
            actual3 = e.getMessage();
        }
        assertEquals(expected3, actual3);
    }


    @Test
    public void ListTest() {
        manager = new taskManager(new FileManager("taskManagerTestDummy"));

        assertEquals(0, manager.getListSize());

        manager.addTask('T', "1st task");
        manager.addTask('D', "2nd task /by 12-12-2001");
        try {
            manager.addTask('D', "invalid task /by Someday");
        } catch (InputException e) {}

        assertEquals(2, manager.getListSize());

        try {
            manager.deleteTask(2);
        } catch (InputException e) {}

        manager.deleteTask(1);
        assertEquals(1, manager.getListSize());
    }
}
