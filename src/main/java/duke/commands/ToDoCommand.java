package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class ToDoCommand extends Command {
    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        String name = parser.nextUntilOption();
        parser.assertEnd();
        Task t = new ToDo(name);
        duke.getUi().print(String.format("Ok, I've added a new todo...\n  %s", t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}
