package controller;

import duke.Storage;

public abstract class Command {
    public abstract void execute(Storage storage);
}