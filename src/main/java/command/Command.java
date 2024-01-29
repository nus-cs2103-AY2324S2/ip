package command;

import run.Storage;
import run.TaskList;
import run.Ui;

import others.BelleException;

public abstract class Command {
    public abstract void execute(Storage s, TaskList t, Ui u) throws BelleException;

    public abstract boolean isExit();
}