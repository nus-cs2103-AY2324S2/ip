package dino.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import dino.Dino;
import dino.DinoException;
import dino.Parser;
import dino.TaskList;
import dino.task.Task;

public class ParserTest {

    @Test
    public void testCreateTaskFromInput() throws DinoException {
        Parser parser = new Parser(new TaskList());

        Task todoTask = parser.createTaskFromInput(Dino.TaskType.TODO, "Buy groceries");
        assertEquals(" T | 0 | Buy groceries", todoTask.toString());

        Task deadlineTask = parser.createTaskFromInput(Dino.TaskType.DEADLINE, "Finish assignment /by 28-02-2022 1800");
        assertEquals(" D | 0 | Finish assignment | by: Feb 28 2022 18:00", deadlineTask.toString());

        Task eventTask = parser.createTaskFromInput(Dino.TaskType.EVENT,
                "Birthday party /from 01-03-2022 1500 /to 01-03-2022 1800");
        assertEquals(" E | 0 | Birthday party | from: Mar 01 2022 15:00 to: Mar 01 2022 18:00", eventTask.toString());
    }

    @Test
    public void testCreateTaskFromInputWithInvalidDeadlineFormat() {
        TaskList taskList = new TaskList();
        String inputTaskDetails = "InvalidDeadlineFormat\n";
        Dino.TaskType taskType = Dino.TaskType.DEADLINE;
        System.setIn(new java.io.ByteArrayInputStream(inputTaskDetails.getBytes()));

        try {
            Task createdTask = Parser.createTaskFromInput(taskType, new Scanner(System.in).nextLine().trim());

            fail("Expected DinoException, but no exception was thrown.");
        } catch (DinoException e) {
            System.setIn(System.in);

            assertEquals("Invalid input format for deadline. "
                    + "Please use: deadline <deadline name> /by dd-mm-yyyy", e.getMessage());
        }
    }
}

