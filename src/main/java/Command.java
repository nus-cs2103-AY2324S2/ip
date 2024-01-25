import java.util.HashSet;

public class Command {
    private String command;

    // Later to be used to determine if
    // terminal command is valid or not
    HashSet<Command> validCommands = new HashSet<>();
}