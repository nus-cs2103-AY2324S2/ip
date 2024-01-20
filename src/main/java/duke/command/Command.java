package duke.command;

public class Command {
    private final String command;
    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return "____________________________________________________________\n" +
                this.command + "\n" +
               "____________________________________________________________\n";
    }
}