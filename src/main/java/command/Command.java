package main.java.command;

import main.java.ChatException;
import main.java.UiHandler;
import main.java.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, UiHandler ui) throws ChatException;
    public abstract boolean isExit();
}