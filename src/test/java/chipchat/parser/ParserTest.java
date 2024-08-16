package chipchat.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;

import chipchat.task.TaskList;
import org.junit.jupiter.api.Test;

import chipchat.action.Action;
import chipchat.action.AddTask;
import chipchat.action.Bye;
import chipchat.action.Delete;
import chipchat.action.ListTasks;
import chipchat.action.Mark;
import chipchat.action.Unmark;
import chipchat.task.Deadline;
import chipchat.task.Event;
import chipchat.task.Task;
import chipchat.task.Todo;

/**
 * Represents a class that tests the Parser.
 */
public class ParserTest {

    /**
     * Tests whether Parser::parseAction will return the correct Action class.
     */
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

//    /**
//     * Tests whether the correct task type is returned from Parser::parseLoadedTask.
//     */
//    @Test
//    public void parseLoadedTask_testInput_returnCorrectTask() {
//        String input;
//        TaskList tasks = new TaskList();
//        AddTask addTask;
//        Task actualTask;
//        Task expectedTask;
//
//        input = "0. TODO /isDone false /description study";
//        addTask = Parser.parseLoadedTask(input);
//        addTask.run();
//        expectedTask = new Todo("study", false);
//        assertEquals(expectedTask.toString(), actualTask.toString());
//
//        input = "1. DEADLINE /isDone false /description homework /by 2023-01-01";
//        actualTask = Parser.parseLoadedTask(input);
//        LocalDate dueBy = Parser.parseDate("2023-01-01");
//        expectedTask = new Deadline("homework", false, dueBy);
//        assertEquals(expectedTask.toString(), actualTask.toString());
//
//        input = "2. EVENT /isDone false /description trip /from 2024-01-01 /to 2024-01-02";
//        actualTask = Parser.parseLoadedTask(input);
//        LocalDate dateFrom = Parser.parseDate("2024-01-01");
//        LocalDate dateTo = Parser.parseDate("2024-01-02");
//        expectedTask = new Event("trip", false, dateFrom, dateTo);
//        assertEquals(expectedTask.toString(), actualTask.toString());
}
