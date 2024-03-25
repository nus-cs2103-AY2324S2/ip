package dude;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DudeTest {
    @Test
    public void testAddToTaskListExpectSizeIncrease() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Duration duration = Duration.ofDays(1).plusHours(2).plusMinutes(30);
        ToDo todo = new ToDo("Sample task", duration);
        taskList.add(todo);

        assertEquals(1, taskList.size(), "TaskList should have exactly one task after adding a ToDo.");
        assertEquals(todo, taskList.get(0), "The task added to TaskList should be the same as the ToDo created.");
    }

    @Test
    public void testMarkAsDoneExpectDone() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Duration duration = Duration.ofDays(1).plusHours(2).plusMinutes(30);
        ToDo todo = new ToDo("Another sample task", duration);
        taskList.add(todo);

        // Assuming the first task has index 0
        taskList.markAsDone(0);

        assertTrue(taskList.get(0).isDone, "The task should be marked as done.");
    }

    @Test
    public void testInvalidInputExpectsThrow() {
        assertThrows(RuntimeException.class, () -> Parser.getCommand("invalid command"));
        assertThrows(RuntimeException.class, () -> Parser.getCommand(""));
        assertThrows(RuntimeException.class, () -> Parser.getCommand(null));
    }

    @Test
    void testByeExpectsBYE() {
        assertEquals(Actions.BYE, Parser.getCommand("bye").action);
    }

    @Test
    void testListInputListExpectListAction() {
        assertEquals(Actions.LIST, Parser.getCommand("list").action);
    }

    @Test
    void testTodoInputTodoExpectTodoAction() {
        assertEquals(Actions.TODO, Parser.getCommand("todo Do something").action);
    }

    @Test
    void testDeadlineInputDeadlineExpectDeadlineAction() {
        assertEquals(Actions.DEADLINE, Parser.getCommand("deadline Complete assignment /by tomorrow").action);
    }

    @Test
    void testEventInputEventExpectEventAction() {
        assertEquals(Actions.EVENT, Parser.getCommand("event Birthday party /at Saturday").action);
    }

    @Test
    void testFindInputFindExpectFindAction() {
        assertEquals(Actions.FIND, Parser.getCommand("find Keyword").action);
    }

    @Test
    void testDoneInputDoneExpectDoneAction() {
        assertEquals(Actions.DONE, Parser.getCommand("done 1").action);
    }

    @Test
    void testUndoInputUndoExpectUndoneAction() {
        assertEquals(Actions.UNDONE, Parser.getCommand("undone 1").action);
    }

    @Test
    void testDeleteInputDeleteExpectDeleteAction() {
        assertEquals(Actions.DELETE, Parser.getCommand("delete 1").action);
    }

}
