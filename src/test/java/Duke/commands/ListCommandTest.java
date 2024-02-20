package duke.commands;

import org.junit.jupiter.api.Test;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;
public class ListCommandTest {
    @Test
    public void executeForStringTest() {
        Storage storage = new Storage("./data/duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        System.out.println(new ListCommand().executeForString(tasks, ui, storage));
    }
}
