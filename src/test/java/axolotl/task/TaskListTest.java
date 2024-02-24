package axolotl.task;


import axolotl.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void addToDoTest() {
        TaskList tasks = new TaskList();
        Task t = new ToDo("todo1", false);

        tasks.addTask(t);

        assertEquals(tasks.getTask(0), t);
        assertEquals("[T][ ] todo1", t.toString());
    }

    @Test
    public void addDeadlineTest() {
        TaskList tasks = new TaskList();
        Task t = new Deadline("deadline1", false, LocalDateTime.parse("11/10/2019 1700" , Parser.dateTimeFormatter));

        tasks.addTask(t);

        assertEquals(tasks.getTask(0), t);
        assertEquals("[D][ ] deadline1 (by: Oct 11 2019 05:00pm)", t.toString());
    }

    @Test
    public void addEventTest() {
        TaskList tasks = new TaskList();
        Task t = new Event("event1", false,
                LocalDateTime.parse("11/10/2019 1700" , Parser.dateTimeFormatter),
                LocalDateTime.parse("12/10/2019 1700" , Parser.dateTimeFormatter));

        tasks.addTask(t);

        assertEquals(tasks.getTask(0), t);
        assertEquals("[E][ ] event1 (from: Oct 11 2019 05:00pm to: Oct 12 2019 05:00pm)", t.toString());
    }

    @Test
    public void markTest(){
        TaskList tasks = new TaskList();
        Task t = new ToDo("todo1", false);

        tasks.addTask(t);
        tasks.markTask(0);

        assertTrue(t.isDone());
    }

    @Test
    public void unmarkTest(){
        TaskList tasks = new TaskList();
        Task t = new ToDo("todo1", false);

        tasks.addTask(t);
        tasks.markTask(0);
        tasks.unmarkTask(0);

        assertTrue(!t.isDone());
    }

    @Test
    public void removeTaskTest(){
        TaskList tasks = new TaskList();
        Task t = new ToDo("todo1", false);

        tasks.addTask(t);
        tasks.deleteTask(0);

        assertTrue(tasks.getTask(0) == null);
    }

}

