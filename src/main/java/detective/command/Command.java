package detective.command;

import detective.DukeException;
import detective.TaskList;
import detective.Ui;

public abstract class Command {
    public void execute(TaskList taskList, Ui ui) throws DukeException { }
}
