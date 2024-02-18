package aaron.parser;

import org.junit.jupiter.api.Test;


import aaron.exception.ParsingException;
import aaron.exception.TaskErrorException;
import aaron.task.TaskList;
import aaron.task.TaskType;
import aaron.ui.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void InvalidCommandTest() {
        UI ui = new UI();
        TaskList taskList = new TaskList();
        assertThrows(ParsingException.class, () -> Parser.parse("hehehoho", taskList, ui));
    }

    @Test
    public void addTaskTest() {
        UI ui = new UI();
        TaskList taskList = new TaskList();
        try {
            Parser.parse("add todo run", taskList, ui);
            ui.closeScanner();
        } catch (ParsingException e) {
            System.out.println(e);
        }
        assertEquals(taskList.getTasklistSize(), 1);
    }

    @Test
    public void markCommandTest() {
        UI ui = new UI();
        TaskList taskList = new TaskList();
        try {
            taskList.addToList(TaskType.TODO, "run");
        } catch (TaskErrorException e) {
            System.out.println(e);
        }
        try {
            Parser.parse("mark 1", taskList, ui);
        } catch (ParsingException e) {
            System.out.println(e);
        }
        assertEquals("[T][X] | run", taskList.printTask(1));
    }

}