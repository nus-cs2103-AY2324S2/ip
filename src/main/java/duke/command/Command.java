package duke.command;

import duke.ui.Ui;
import duke.TaskList;
import duke.DukeException;

public interface Command {

    String execute(TaskList tasks, Ui ui) throws DukeException;

    boolean isExit();
}
