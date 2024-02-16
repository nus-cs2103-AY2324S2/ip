package tyler.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tyler.storage.Storage;
import tyler.task.Task;
import tyler.task.TaskList;
import tyler.task.Todo;
import tyler.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void testDelete1() {
        Todo task1 = new Todo("readbook");
        Todo task2 = new Todo("hello");
        Todo task3 = new Todo("CS2103T iP");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        TaskList tasks = new TaskList(taskList);
        Ui ui = new Ui();
        Storage storage = new Storage("");
        DeleteCommand delete = new DeleteCommand(1);
        delete.execute(tasks, ui, storage);
        assertEquals(2, taskList.size());
        assertEquals(task2.toString(), taskList.get(0).toString());
        assertEquals(task3.toString(), taskList.get(1).toString());

    }

    @Test
    public void testDelete2() {
        Todo task1 = new Todo("readbook");
        Todo task2 = new Todo("hello");
        Todo task3 = new Todo("CS2103T iP");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        TaskList tasks = new TaskList(taskList);
        Ui ui = new Ui();
        Storage storage = new Storage("");
        DeleteCommand delete = new DeleteCommand(2);
        delete.execute(tasks, ui, storage);
        assertEquals(2, taskList.size());
        assertEquals(task1.toString(), taskList.get(0).toString());
        assertEquals(task3.toString(), taskList.get(1).toString());
    }
}
