package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class ToDoCommand extends Command {

    private static final String SUCCESS_MSG = "Ok, I've added a new todo...\n  %s";

    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        String name = parser.nextUntilOption();
        parser.assertEnd();
        Task t = new ToDo(name);
        duke.getUi().print(String.format(SUCCESS_MSG, t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}
