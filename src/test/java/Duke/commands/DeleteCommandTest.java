package duke.commands;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;


public class DeleteCommandTest {
    @Test
    public void executeForStringTest() {
        Storage storage = new Storage("./data/duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        String[] words = {"todo", "testDelete"};
        String[] words1 = {"delete", "5"};
        System.out.println(new ListCommand().executeForString(tasks, ui, storage));

    }
    @Test
    public void clearList() {
        Storage storage = new Storage("./data/duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            tasks.delete(0);
        }
        storage.rewriteFile(tasks.getTasks());
    }
}
