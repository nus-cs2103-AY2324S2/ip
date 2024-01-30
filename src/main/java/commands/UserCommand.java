package commands;

/**
 * Represents the result of a command execution.
 * */
public class UserCommand {
    private final String messageToUser;

    public UserCommand(String ...args) {
        StringBuilder sb = new StringBuilder();
        for (String arg: args) {
            sb.append(arg);
            sb.append("\n");
        }
        this.messageToUser = sb.toString();
    }

    public String getMessageToUser() {
        return this.messageToUser;
    }
}
