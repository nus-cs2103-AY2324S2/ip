package chipchat.parser;

import chipchat.action.*;
import chipchat.parser.Parser;
import chipchat.task.Deadline;
import chipchat.task.Event;
import chipchat.task.Task;
import chipchat.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseAction_feedUserInput_returnCorrectAction() {
        Action action;

        action = Parser.parseAction("bye");
        assertInstanceOf(Bye.class, action);

        action = Parser.parseAction("list");
        assertInstanceOf(ListTasks.class, action);

        action = Parser.parseAction("delete 0");
        assertInstanceOf(Delete.class, action);

        action = Parser.parseAction("mark 0");
        assertInstanceOf(Mark.class, action);

        action = Parser.parseAction("unmark 0");
        assertInstanceOf(Unmark.class, action);

        action = Parser.parseAction("todo study");
        assertInstanceOf(AddTask.class, action);

        action = Parser.parseAction("deadline homework /by 2024-01-01");
        assertInstanceOf(AddTask.class, action);

        action = Parser.parseAction("event trip /from 2024-01-01 /to 2024-01-02");
        assertInstanceOf(AddTask.class, action);
    }

    @Test
    public void parseLoadedTask_testInput_returnCorrectTask() {
        String input;
        Task actualTask;
        Task expectedTask;

        input = "0. TODO /isDone false /description study";
        actualTask = Parser.parseLoadedTask(input);
        expectedTask = new Todo("study", false);
        assertEquals(expectedTask.toString(), actualTask.toString());

        input = "1. DEADLINE /isDone false /description homework /by 2023-01-01";
        actualTask = Parser.parseLoadedTask(input);
        LocalDate dueBy = Parser.parseDate("2023-01-01");
        expectedTask = new Deadline("homework", false, dueBy);
        assertEquals(expectedTask.toString(), actualTask.toString());

        input = "2. EVENT /isDone false /description trip /from 2024-01-01 /to 2024-01-02";
        actualTask = Parser.parseLoadedTask(input);
        LocalDate dateFrom = Parser.parseDate("2024-01-01");
        LocalDate dateTo = Parser.parseDate("2024-01-02");
        expectedTask = new Event("trip", false, dateFrom, dateTo);
        assertEquals(expectedTask.toString(), actualTask.toString());
    }
}
