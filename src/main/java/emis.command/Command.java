package main.java.emis.command;
import main.java.emis.*;

public abstract class Command {
    public abstract void execute(TaskList t, Ui ui, Storage s);
    public abstract boolean isExit();
}