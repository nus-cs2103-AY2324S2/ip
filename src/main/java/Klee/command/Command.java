package Klee.command;

import Klee.KleeException;

import Klee.Ui;
import Klee.Storage;
import Klee.TaskList;

public abstract class Command {
    public abstract void runCommand(Ui ui, Storage storage, TaskList tasks) throws KleeException;
}