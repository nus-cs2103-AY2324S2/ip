package bob;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BobTest {
    @Test
    public void todoTask_todoCreation_correctSaveFormat(){
        try {
            Task t = new Task("Homework");
            assertEquals("false|" + t.uuid + "|T|Homework|false", t.toSavableFormat());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deadlineTask_deadlineCreation_correctDateRecognition(){
        try {
            Deadline d = new Deadline("Homework", "2/12/2019");
            assertEquals(d.getDateTime(), LocalDateTime.of(2019, 12, 2, 0, 0));
            assertEquals(false, d.hasTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void taskList_commandRecognition_correctErrorResponse(){

        try {
            BobTaskList btl = new BobTaskList(null, null);
            btl.handleTaskCreation("todo");
        } catch (BobException e) {
            assertEquals("The command todo requires a task description.", e.getMessage());
        }

        try {
            BobTaskList btl = new BobTaskList(null, null);
            btl.handleTaskCreation("event test /from now");
        } catch (BobException e) {
            assertEquals("The command event requires a task description, a start date, and an end date.", e.getMessage());
        }

        try {
            BobTaskList btl = new BobTaskList(null, null);
            btl.handleTaskCreation("deadline test");
        } catch (BobException e) {
            assertEquals("The command deadline requires both a task description and a deadline.", e.getMessage());
        }

    }
}