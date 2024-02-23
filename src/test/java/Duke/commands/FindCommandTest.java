package duke;

import org.junit.jupiter.api.Test;

import duke.commands.DeadlineCommand;
import duke.commands.FindCommand;
import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;


public class FindCommandTest {
    @Test
    public void executeForStringTest() {
        Storage storage = new Storage("./data/duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        try {
            String input = "find test FindCommand";
            String[] words = input.split(" ", 2);
            String testInput = "deadline test FindCommand /by today";
            System.out.println(new DeadlineCommand(testInput.split(" ", 2)).executeForString(tasks, ui, storage));
            System.out.println(new FindCommand(words).executeForString(tasks, ui, storage));
        } catch (DukeException e) {
            System.out.println(ui.exceptionMsg(e));
        }
    }
}