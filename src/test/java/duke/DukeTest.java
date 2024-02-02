package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        // Create a sample task list and initialize necessary objects
        ui = new Ui("Hammy");
        taskList = new TaskList(new ArrayList<>(), ui);
        parser = new Parser(ui, new Storage("./data/duke.txt"), taskList);
    }

    @Test
    public void testTodoTaskToString() {
        // Test the toString() method of TodoTask
        Task todoTask = new TodoTask("Buy groceries");
//        String expected = "[T][ ] Buy groceries";
        String expected = "____________________________________________________________\n" +
                "Got it. I have added [T][ ] Buy groceries into Task List.\n" +
                "____________________________________________________________"
        assertEquals(expected, todoTask.toString());
    }

    @Test
    public void testDeadlineTaskToString() {
        // Test the toString() method of DeadlineTask
        LocalDate deadline = LocalDate.of(2022, 12, 31);
        Task deadlineTask = new DeadlineTask("Submit report", deadline);
//        String expected = "[D][ ] Submit report (by: Dec 31 2022)";
        String expected = "____________________________________________________________\n" +
                "Got it. I have added [D][ ] Submit report (by: Dec 31 2022) into Task List.\n" +
                "____________________________________________________________"
        assertEquals(expected, deadlineTask.toString());
    }
}
