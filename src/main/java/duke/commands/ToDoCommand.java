package duke.commands;

import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.DukeException;
import duke.DukeOptionParsingException;
import duke.Parser;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.ToDo;


public class ToDoCommand extends Command {
    @Override
    public void run(Parser parser, Duke duke) throws DukeException {
        String str = parser.rest();
        Task t = new ToDo(str);
        duke.print(String.format("Ok, I've added a new todo...\n  %s", t.describe()));
        duke.getTasks().add(t);
        duke.getStorage().writeTasks(duke.getTasks());
    }
}
