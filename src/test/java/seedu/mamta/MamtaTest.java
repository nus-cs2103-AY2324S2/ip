package seedu.mamta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
/*
public class MamtaTest {

    private static final String LOAD_TASKS_FILE = "./data/mamtainput.txt";
    private static final String INPUT_NEW_TASKS_FILE = "./text-ui-test/input.txt";

    @BeforeEach
    void setUp() throws FileNotFoundException {
        // Clearing any existing tasks and setting up a clean state for each test
        TaskList.clear();
    }

    @Test
    void testGreet() {
        assertEquals("Hello! I'm Mamta\nWhat can I do for you?", Mamta.greet());
    }

    @Test
    void testExit() {
        assertEquals("Bye. Hope to see you again soon!", Mamta.exit());
    }

    @Test
    void testEchoBye() {
        assertEquals("------------------------------------------\nBye. Hope to see you again soon!\n------------------------------------------", Mamta.echo("todo", "bye", 0, "", ""));
    }

    @Test
    void testEchoList() {
        // Add some tasks to the TaskList for testing
        TaskList.addTask(new Todo(false,"Task 1"));
        TaskList.addTask(new Todo(false,"Task 2"));

        String expectedOutput = "------------------------------------------\n1. T| |Task 1\n2. T| |Task 2\n------------------------------------------";
        assertEquals(expectedOutput, Mamta.echo("todo", "list", 0, "", ""));
    }

    // Add more test methods as needed for other functionalities

    @Test
    void testRun() throws FileNotFoundException {
        Mamta.run(LOAD_TASKS_FILE, INPUT_NEW_TASKS_FILE);
        // Add assertions to verify that the method executed as expected
    }
}
*/