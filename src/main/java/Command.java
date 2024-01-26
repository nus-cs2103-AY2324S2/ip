import java.util.HashSet;

public class Command {
    private String command;
    private String index;

    // Constructor
    public Command(String command, String index) {
        this.command = command;
        if (indexCommands.contains(command)) {
            this.index = index;
        } else {
            this.index = "";
        }
    }

    // Set to check if command is valid
    private static HashSet<String> indexCommands = new HashSet<String>() {
        {
            add("delete");
            add("mark");
            add("unmark");
        }
    };

    private static HashSet<String> oneWordCommands = new HashSet<String>() {
        {
            add("list");
            add("bye");
            add("help");
        }
    };

    private static HashSet<String> validCommands = new HashSet<String>() {
        {
            addAll(indexCommands);
            addAll(oneWordCommands);
        }
    };

    // Method to check is command is valid
    public boolean isValidCommand(String command) {
        return validCommands.contains(command);
    }

    // Method to return string representation of command
    public String toString() {
        return this.command;
    }
}