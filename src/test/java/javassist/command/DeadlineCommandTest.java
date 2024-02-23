package javassist.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import javassist.exception.JavAssistException;
import javassist.task.Deadline;
import javassist.util.StorageStub;
import javassist.util.TaskList;
import javassist.util.Ui;



public class DeadlineCommandTest {

    @Test
    public void execute_deadlineFormat_deadline() {
        DeadlineCommand dc = new DeadlineCommand("deadline return book /by 20-12-2020 10:01");
        Deadline dl = new Deadline("return book", "20-12-2020 10:01");
        TaskList list = new TaskList();
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            assertEquals("D | 0 | return book | 20-12-2020 10:01", list.getTask(0).toString());
        } catch (JavAssistException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_deadlineWrongDateFormat_javassistException() {
        DeadlineCommand dc = new DeadlineCommand("deadline return book /by 20/12/2020 10:01");
        TaskList list = new TaskList();
        try {
            dc.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            fail();
        } catch (JavAssistException e) {
            assertEquals("Invalid due date. Specify date in format 'dd-MM-yyyy HH:mm'.", e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }
}
