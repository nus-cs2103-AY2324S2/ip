package duke;

public interface CommandBuilder {
    public Command build(String argument) throws InvalidCommandException;
}
