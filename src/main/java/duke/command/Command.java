package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public void execute(TaskList taskList, Ui ui) throws DukeException { }
}
