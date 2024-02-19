package duke.commands;

import duke.ChatSession;
import duke.exceptions.InvalidParametersException;
import duke.tasks.ToDo;

/**
 * ToDoCommand class to handle the execution of todo command
 */
public class ToDoCommand implements NamedCommand {
    public String getName() {
        return "todo";
    }

    /**
     * Adds a todo task to the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     * @throws InvalidParametersException If the command arguments are invalid
     */
    public void execute(ChatSession session, String commandArgs) throws InvalidParametersException {
        if (commandArgs.equals("")) {
            throw new InvalidParametersException("parameters for todo cannot be empty");
        }
        ToDo t = new ToDo(commandArgs);
        session.getTaskList().add(t);

        session.printMessage(String.format("Got it. I've added the following task: \n %s", t.getName()));
    }
}
