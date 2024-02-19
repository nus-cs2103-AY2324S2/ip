package duke.commands;

import duke.ChatSession;
import duke.tasks.Task;

/**
 * Mark class to handle the execution of mark command
 */
public class Mark implements NamedCommand {
    public String getName() {
        return "mark";
    }

    /**
     * Marks a task as done
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        int index = Integer.valueOf(commandArgs);
        Task t = session.getTaskList().getTask(index);
        t.mark();
        session.printMessage("Nice! I've marked this task as done:" + System.lineSeparator() + t.getName());
    }
}
