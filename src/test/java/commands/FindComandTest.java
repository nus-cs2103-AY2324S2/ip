package commands;

import exceptions.HowieException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import task.Deadline;
import task.Task;
import tasklists.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FindComandTest {

    //@@author Jonathon Cook
    //Reused from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void findCommand_textInput_showsCorrectResult() throws HowieException, IOException {
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("return book", "tomorrow"));
        tasks.add(new Task("read book"));
        tasks.add(new Task("buy groceries"));

        String input = "book";
        Command command = new FindCommand(input);
        command.setData(tasks);
        command.executeCommand();
        assertEquals("Here are the list of tasks that matches your keyword: [book]\n" +
                        "1. [D][ ] return book(by: tomorrow)\r\n" +
                        "2. [T][ ] read book",
                outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();

        input = "exams";
        command = new FindCommand(input);
        command.setData(tasks);
        command.executeCommand();
        assertEquals("Here are the list of tasks that matches your keyword: [exams]",
                outputStreamCaptor.toString().trim());
    }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
