package hammy;
import hammy.storage.Storage;
import hammy.task.*;
import hammy.response.*;
import hammy.parser.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        // Create a sample task list and initialize necessary objects
        ui = new Ui("Hammy");
        taskList = new TaskList(new ArrayList<>(), ui);
        parser = new Parser(ui, new Storage("./data/tasklist.txt"), taskList);
    }

    @Test
    public void testDeadlineTaskToString1() {
        // Test the toString() method of DeadlineTask
        LocalDate deadline = LocalDate.of(2022, 12, 31);
        Task deadlineTask = new DeadlineTask("Submit report", deadline);
        String expected = "[D][ ] Submit report (by: Dec 31 2022)";
        assertEquals(expected, deadlineTask.toString());
    }

    @Test
    public void testDeadlineTaskToString2() {
        // Test the toString() method of DeadlineTask
        LocalDate deadline = LocalDate.of(2023, 3, 2);
        Task deadlineTask = new DeadlineTask("Finish A-JUnit", deadline);
        String expected = "[D][ ] Finish A-JUnit (by: Mar 2 2023)";
        assertEquals(expected, deadlineTask.toString());
    }
}
