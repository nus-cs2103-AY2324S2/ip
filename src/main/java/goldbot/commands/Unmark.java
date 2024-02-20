package goldbot.commands;

import goldbot.ChatSession;
import goldbot.tasks.Task;

/**
 * Unmark class to handle the execution of unmark command
 */
public class Unmark implements NamedCommand {
    public String getName() {
        return "unmark";
    }

    /**
     * Unmarks a task from the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        int index = Integer.valueOf(commandArgs);
        Task t = session.getTaskList().getTask(index);
        t.unmark();
        session.printMessage("OK, I've marked this task as not done yet:" + System.lineSeparator() + t.getName());
    }
}
