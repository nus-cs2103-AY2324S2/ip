package CinnamoRoll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestTaskList {
    @Test
    void testMark() throws Exception {
        TaskList task = new TaskList();
        task.respondUser("deadline MA5211 Assignment 1 /by 2024-02-05 14:00");
        task.respondUser("mark 1");
        assertEquals("[D][X] MA5211 Assignment 1 (by: Feb 05 2024 14:00)", task.getUser(0).toString(),
                "Testing the marking for the task event from the list of events");
    }
    @Test
    void testExecute() throws Exception {
        TaskList task = new TaskList();
        task.respondUser("todo what to do");
        assertEquals("[T][ ] what to do", task.getUser(0).toString(),
                "Checking whether the event is added properly into the list "
                        + "with correct class of the object");
    }
    @Test
    void testDelete() throws Exception {
        TaskList task = new TaskList();
        task.respondUser("todo what to do");
        task.respondUser("deadline MA5211 Assignment 1 /by 2024-02-05 14:00");
        task.respondUser("todo cs2103t ip");
        task.respondUser("delete 2");
        task.respondUser("mark 2");
        assertEquals("[T][X] cs2103t ip", task.getUser(1).toString(),
                "Test should return the third event of the list before "
                        + "deletion if done properly");
    }
}
