package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import duke.exception.InvalidArgumentException;

public class MarkCommand extends Command {

    private int positionToMark;

    public MarkCommand(String position) {
        this.positionToMark = Integer.parseInt(position);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            taskList.markTask(positionToMark);
            ui.displayMarkedTask(taskList, positionToMark);
            Storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("The index is out of range >.<");
        }

    }
}