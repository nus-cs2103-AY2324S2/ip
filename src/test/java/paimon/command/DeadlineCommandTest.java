package paimon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paimon.ChatException;
import paimon.task.TaskList;
import paimon.util.UiHandler;




public class DeadlineCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void execute_endDateValidFormat_success() throws Exception {
        TaskList taskList = new TaskList();
        UiHandler ui = new UiHandler();
        ArrayList<Command> validDateCommands = new ArrayList<Command>();
        validDateCommands.add(new DeadlineCommand("buy donuts 1", "2022/01/02 01:01"));
        validDateCommands.add(new DeadlineCommand("buy donuts 2", "2022/01/02"));
        validDateCommands.add(new DeadlineCommand("do stuff 1", "2023-02-01 10:01"));
        validDateCommands.add(new DeadlineCommand("do stuff 2", "2023-02-01"));
        validDateCommands.add(new DeadlineCommand("fly kite 1", "13/05/2001 13:04"));
        validDateCommands.add(new DeadlineCommand("fly kite 2", "13/05/2001"));

        validDateCommands.get(0).execute(taskList, ui);
        assertEquals("Okay Traveller, I've added the following task!\n"
                + "-------------------->\n[D][ ] buy donuts 1 (by: 2022-01-02 01:01)\n"
                + "-------------------->\nYou have 1 tasks in your list", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        validDateCommands.get(1).execute(taskList, ui);
        assertEquals("Okay Traveller, I've added the following task!\n"
                + "-------------------->\n[D][ ] buy donuts 2 (by: 2022-01-02 00:00)\n"
                + "-------------------->\nYou have 2 tasks in your list", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        validDateCommands.get(2).execute(taskList, ui);
        assertEquals("Okay Traveller, I've added the following task!\n"
                + "-------------------->\n[D][ ] do stuff 1 (by: 2023-02-01 10:01)\n"
                + "-------------------->\nYou have 3 tasks in your list", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        validDateCommands.get(3).execute(taskList, ui);
        assertEquals("Okay Traveller, I've added the following task!\n"
                + "-------------------->\n[D][ ] do stuff 2 (by: 2023-02-01 00:00)\n"
                + "-------------------->\nYou have 4 tasks in your list", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        validDateCommands.get(4).execute(taskList, ui);
        assertEquals("Okay Traveller, I've added the following task!\n"
                + "-------------------->\n[D][ ] fly kite 1 (by: 2001-05-13 13:04)\n"
                + "-------------------->\nYou have 5 tasks in your list", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
        validDateCommands.get(5).execute(taskList, ui);
        assertEquals("Okay Traveller, I've added the following task!\n"
                + "-------------------->\n[D][ ] fly kite 2 (by: 2001-05-13 00:00)\n"
                + "-------------------->\nYou have 6 tasks in your list", outputStreamCaptor.toString().trim());
        outputStreamCaptor.reset();
    }
    @Test
    public void execute_taskNumberOutOfIndex_exceptionThrown() throws Exception {
        TaskList taskList = new TaskList();
        UiHandler ui = new UiHandler();
        ArrayList<Command> validDateCommands = new ArrayList<Command>();
        validDateCommands.add(new DeadlineCommand("buy donuts", "2022-14-01"));
        validDateCommands.add(new DeadlineCommand("buy donuts", "2022-12-01 29:01"));
        validDateCommands.add(new DeadlineCommand("do stuff", "2023/15/01"));
        validDateCommands.add(new DeadlineCommand("do stuff", "40/12/2001"));
        validDateCommands.add(new DeadlineCommand("fly kite", "01/30/2003"));
        validDateCommands.add(new DeadlineCommand("fly kite", "random string here"));
        assertThrows(ChatException.class, () -> validDateCommands.get(0).execute(taskList, ui));
        assertThrows(ChatException.class, () -> validDateCommands.get(1).execute(taskList, ui));
        assertThrows(ChatException.class, () -> validDateCommands.get(2).execute(taskList, ui));
        assertThrows(ChatException.class, () -> validDateCommands.get(3).execute(taskList, ui));
        assertThrows(ChatException.class, () -> validDateCommands.get(4).execute(taskList, ui));
        assertThrows(ChatException.class, () -> validDateCommands.get(5).execute(taskList, ui));

    }
}
