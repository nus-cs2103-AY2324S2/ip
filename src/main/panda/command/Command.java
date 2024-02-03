package panda.command;
import panda.exception.PandaException;
import panda.component.*;
public abstract class Command {
    abstract public boolean isExit();
    abstract public void execute(TaskList tlist) throws PandaException;
    abstract public void execute(TaskList tlist, Ui ui, Storage cacheFile) throws PandaException;
}
