package controller;

import duke.Storage;

public abstract class TaskCommand {
    public abstract void execute(Storage storage);
}