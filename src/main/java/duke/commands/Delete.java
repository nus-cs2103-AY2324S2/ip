package duke.commands;

import duke.ChatSession;
import duke.tasks.Task;


/**
 * Delete class to handle the execution of delete command
 */
public class Delete implements NamedCommand {
    public String getName() {
        return "delete";
    }

    /**
     * Deletes a task from the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        int index = Integer.valueOf(commandArgs);
        Task t = session.getTaskList().pop(index);
        session.printMessage("Nice! I've deleted this task:" + System.lineSeparator() + t.getName());
    }
}
