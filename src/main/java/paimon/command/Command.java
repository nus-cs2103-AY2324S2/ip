package paimon.command;


import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, UiHandler ui) throws ChatException;
    public abstract boolean isExit();
}