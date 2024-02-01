package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.Task;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

public class Command {
    private int taskNum;
    private Task newTask;
    public Command(int taskNum) {
        this.taskNum = taskNum;
    }

    public Command(Task task) {
        this.newTask = task;
    }
    public Command() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }


}

