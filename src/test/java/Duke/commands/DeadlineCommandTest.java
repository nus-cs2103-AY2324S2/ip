package duke.commands;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;
public class DeadlineCommandTest {
    @Test
    public void executeForStringTest() {
        Storage storage = new Storage("./duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        try {
            String input = "deadline eat food /by 20/12/20 0000";
            String[] words = input.split(" ", 2);
            System.out.println(new DeadlineCommand(words).executeForString(tasks, ui, storage));
        } catch (DukeException e) {
            System.out.println(ui.exceptionMsg(e));
        }
    }
}