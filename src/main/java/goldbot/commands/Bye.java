package goldbot.commands;

import goldbot.ChatSession;

/**
 * Bye command to allow user to exit the chat
 */
public class Bye implements NamedCommand {
    private static final String NAME = "bye";

    public String getName() {
        return Bye.NAME;
    }

    /**
     * Executes the command
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        session.printMessage("Bye. Hope to see you again soon!");
        session.terminateChat();
    }
}
