package tofu.command;

import tofu.Tofu;
import tofu.TofuException;
import tofu.ui.Ui;
import tofu.task.TaskList;

public interface Command {
    String execute(TaskList tasks, Ui ui) throws TofuException;
    boolean isExit();
}
