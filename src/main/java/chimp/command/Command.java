package chimp.command;
import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException;
    public abstract boolean isExit();
}