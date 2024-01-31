package duke.command;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void processCommandByeCommand_shouldSetRunningStatusToFalse() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, scanner, true);

        parser.setCommand("bye");
        parser.processCommand();

        assertFalse(parser.getRunningStatus());
    }

    @Test
    void processCommandListCommand_shouldPrintTaskList() throws DukeException { //only line separators is an issue, but produces correct output
        TaskList taskList = new TaskList();
        taskList.addToDoTask(new ToDo("Test Task"));
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, scanner, true);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.setCommand("list");
        parser.processCommand();

        assertEquals("-------------------------------\n" +
                "Here is the task in your list:\n1.[T][ ] Test Task\n-------------------------------", outputStream.toString().trim());

        System.setOut(System.out);
    }

}
