package CinnamoRoll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class TestTaskList {
    @Test
    void testMark() throws Exception {
        TaskList task = new TaskList();
        task.respond("deadline MA5211 Assignment 1 /by 2024-02-05 14:00");
        task.respond("mark 1");
        assertEquals("[D][X] MA5211 Assignment 1 (by: Feb 05 2024 14:00)", task.get(0).toString(),
                "Testing the marking for the task event from the list of events");
    }
    @Test
    void testExecute() throws Exception {
        TaskList task = new TaskList();
        task.respond("todo what to do");
        assertEquals("[T][ ] what to do", task.get(0).toString(),
                "Checking whether the event is added properly into the list " +
                        "with correct class of the object");
    }
    @Test
    void testDelete() throws Exception {
        TaskList task = new TaskList();
        task.respond("todo what to do");
        task.respond("deadline MA5211 Assignment 1 /by 2024-02-05 14:00");
        task.respond("todo cs2103t ip");
        task.respond("delete 2");
        task.respond("mark 2");
        assertEquals("[T][X] cs2103t ip", task.get(1).toString(),
                "Test should return the third event of the list before " +
                        "deletion if done properly");
    }
}