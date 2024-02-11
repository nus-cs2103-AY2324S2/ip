package Aaron.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.beans.Transient;

import Aaron.Exception.AaronBotException;
import Aaron.Exception.ParsingException;
import Aaron.Exception.TaskErrorException;
import Aaron.Task.TaskList;
import Aaron.Task.TaskType;
import Aaron.UI.UI;

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