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

public class EventTaskTest {
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
    public void testEventTaskToString1() {
        // Test the toString() method of EventTask
        LocalDate start = LocalDate.of(2021, 12, 31);
        LocalDate end = LocalDate.of(2022, 1, 31);
        Task eventTaskTest1 = new EventTask("Submit report", start, end);
        String expected = "[E][ ] Submit report (from: Dec 31 2021 to: Jan 31 2022)";
        assertEquals(expected, eventTaskTest1.toString());
    }

    @Test
    public void testEventTaskToString2() {
        // Test the toString() method of EventTask
        LocalDate start = LocalDate.of(2023, 8, 18);
        LocalDate end = LocalDate.of(2023, 8, 19);
        Task eventTaskTest1 = new EventTask("Supernova", start, end);
        String expected = "[E][ ] Supernova (from: Aug 18 2023 to: Aug 19 2023)";
        assertEquals(expected, eventTaskTest1.toString());
    }
}
