package shon.command;

import java.time.format.DateTimeParseException;

import shon.exception.ParameterException;

import shon.TaskList;
import shon.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui)
            throws ParameterException, DateTimeParseException;

    public boolean isExit() {
        return false;
    }
}
