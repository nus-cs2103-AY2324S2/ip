package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.Todo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    private Storage storage;
    private TaskList tasks;

    public FindCommandTest() {
        this.storage = new Storage("");
        this.tasks = new TaskList();

        Task task1 = new Todo("test 1");
        Task task2 = new Deadline("return book",
                LocalDateTime.of(2024, 1, 1, 18, 0));
        Task task3 = new Todo("test 2");
        Task task4 = new Todo("homework");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks.addTask(task4);
    }

    @Test
    public void execute_normalInput_success() {
        FindCommand f = new FindCommand("test");
        String response = f.execute(tasks, storage);
        String correct = "Here are the matching tasks!\n"
                + "1. test 1\n"
                + "2. test 2\n";
        assertEquals(response, correct);
    }
}
