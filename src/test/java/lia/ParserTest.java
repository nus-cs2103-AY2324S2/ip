package lia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void markTaskAsDone_ValidTask_MarksTaskAsDone() throws LiaException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addTodoTask("Test Task");
        Parser parser = new Parser(ui, taskList);

        parser.markTaskAsDone("mark 1");

        assertEquals(true, taskList.getTask(0).isDone());
    }

    @Test
    public void deleteTask_ValidTask_RemovesTask() throws LiaException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        taskList.addTodoTask("Test Task");
        Parser parser = new Parser(ui, taskList);

        parser.deleteTask("delete 1");

        assertEquals(0, taskList.getSize());
    }

    @Test
    public void markTaskAsDone_InvalidTask_ThrowsLiaException() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(ui, taskList);

        assertThrows(LiaException.class, () -> parser.markTaskAsDone("mark 1"));
    }

    @Test
    public void deleteTask_InvalidTask_ThrowsLiaException() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(ui, taskList);

        assertThrows(LiaException.class, () -> parser.deleteTask("delete 1"));
    }
}
