package duke.commands;

import duke.ChatSession;

/**
 * ListTasks class to handle the execution of list command
 */
public class ListTasks implements NamedCommand {
    public String getName() {
        return "list";
    }

    /**
     * Lists all the tasks in the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        session.printMessage("Here are the tasks in your list:"
            + System.lineSeparator()
            + session.getTaskList().generateName());
    }
}
