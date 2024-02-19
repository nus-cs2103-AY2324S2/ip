package oop;

import lemona.oop.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import lemona.task.Deadline;
import lemona.task.Event;
import lemona.task.Todo;

public class TaskListTest {

    @Test
    public void add_todoTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("todoTest"));

        //normal adding a Todo task into TaskList
        assertEquals("[T][ ] todoTest", tasks.get(0).print());

        tasks.add(new Deadline("deadlineTest", "01/01/2024 1500"));

        //normal adding a Deadline task into TaskList
        assertEquals("[D][ ] deadlineTest(by: Jan 01 2024 1500)", tasks.get(1).print());

        tasks.add(new Event("eventTest", "01/01/2024 1500", "01/01/2024 1600"));

        //normal adding an Event task into TaskList
        assertEquals("[E][ ] eventTest(from: Jan 01 2024 1500) (to: Jan 01 2024 1600)", tasks.get(2).print());
    }

    @Test
    public void add_duplicateTasks_exceptionThrown() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Test"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasks.add(new Todo("Test"));

        System.setOut(System.out);
        String actualOutput = outContent.toString().trim();
        String output = "\t______________________________________________________"
                + "\n\t I think you haven't had enough vitamin D."
                + "\n\t Your task is already existing in the list!"
                + "\n\t I suggest you take some LEMONA"
                + "\n\t______________________________________________________";

        assertEquals(output.trim(), actualOutput.replace("\r\n", "\n"));
    }
}
