package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.DukeException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
