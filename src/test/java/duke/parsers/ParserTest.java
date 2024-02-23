package duke.parsers;

import duke.commands.Command;
import duke.tasks.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
//    @Test
//    public void testEmptyMarkTaskNumber() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        // Method to test
//        Parser.parse("mark").execute();
//        // Get the output of the method
//        String output = outContent.toString().trim();
//
//        System.setErr(System.err);
//        assertEquals("Please state which task you want to mark.", output);
//    }
//    @Test
//    public void testInvalidMarkTaskNumber() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        // Method to test
//        Parser.parse("mark a").execute();
//        // Get the output of the method
//        String output = outContent.toString().trim();
//
//        System.setErr(System.err);
//        assertEquals("Unable to parse the input as an integer. Please put a number after mark.", output);
//    }
//
//    @Test
//    public void testOutOfBoundMarkTaskNumber() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        // Method to test
//        Ui ui = new Ui();
//        TaskList tasks = new TaskList();
//        Parser.parse("mark 1").execute(ui, tasks);
//        // Get the output of the method
//        String output = outContent.toString().trim();
//
//        System.setErr(System.err);
//        assertEquals("Please look carefully. This task is not inside the task list.", output);
//        assertFalse(Parser.parse("mark 1").execute(ui, tasks));
//    }
//
//    @Test
//    public void testMissingDescriptionForDeadline() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        // Method to test
//        Ui ui = new Ui();
//        TaskList tasks = new TaskList();
//        Parser.parse("deadline").execute(ui, tasks);
//        // Get the output of the method
//        String output = outContent.toString().trim();
//
//        System.setErr(System.err);
//        assertEquals("OOPS! The description of a deadline cannot be empty.", output);
//    }
//
//    @Test
//    public void testMissingDescription2ForDeadline() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        // Method to test
//        Ui ui = new Ui();
//        TaskList tasks = new TaskList();
//        Parser.parse("deadline by").execute(ui, tasks);
//        // Get the output of the method
//        String output = outContent.toString().trim();
//
//        System.setErr(System.err);
//        assertEquals("OOPS! You forget to write the task description. Please follow this format: " +
//                "'<task_description> by <deadline>' in yyyy-mm-dd HHmm 24-hr format", output);
//    }
//
//    @Test
//    public void testMissingKeywordForDeadline() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setErr(new PrintStream(outContent));
//        // Method to test
//        Ui ui = new Ui();
//        TaskList tasks = new TaskList();
//        Parser.parse("deadline return book").execute(ui, tasks);
//        // Get the output of the method
//        String output = outContent.toString().trim();
//
//        System.setErr(System.err);
//        assertEquals("OOPS! 'by' keyword is missing. You are required to state the deadline " +
//                "using the 'by' keyword.", output);
//    }
}
