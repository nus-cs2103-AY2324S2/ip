package duke.commands;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;
public class NameCommandTest {
    @Test
    public void executeForStringTest() {
        Storage storage = new Storage("./data/duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        try {
            String input = "name";
            String[] words = input.split(" ", 2);
            System.out.println(new NameCommand(words).executeForString(tasks, ui, storage));
        } catch (DukeException e) {
            System.out.println(ui.exceptionMsg(e));
        }
    }
}