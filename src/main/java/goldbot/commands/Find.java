package goldbot.commands;

import goldbot.ChatSession;

public class Find implements NamedCommand {
    public String getName() {
        return "find";
    }

    public void execute(ChatSession session, String commandArgs) {
        session.printMessage("Here are the matching tasks in your list:"
            + System.lineSeparator()
            + session.getTaskList().findTasks(commandArgs));
    }
}
