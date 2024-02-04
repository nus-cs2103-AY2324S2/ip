import java.time.format.DateTimeParseException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui)
            throws ParameterException, DateTimeParseException;

    public boolean isExit() {
        return false;
    }
}
