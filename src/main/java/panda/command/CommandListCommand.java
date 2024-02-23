package panda.command;

import panda.component.*;

import panda.exception.PandaException;

public class CommandListCommand extends Command {
    public void execute(TaskList tlist) {
        return;
    }

    /**
     * Generates a string of current TaskList
     * 
     * @param tlist the TaskList into which the task is inserted.
     * @param cacheFile the cache file to save changes to.
     * @return the TaskList containing all matches
     * @throws PandaException if an error occurs during execution.
     */
    public String execute(TaskList tlist, Storage cacheFile) throws PandaException {
        assert tlist != null;
        return "Here's the list of commands: \n"
            + "[help] shows the list of commands\n" 
            + "[list] shows the current tasks\n" 
            + "[todo <Task Name>] adds a Todo task to the task list\n" 
            + "[deadline <Task Name> /by <JUN 12 2024>] adds a Deadline task to the task list\n" 
            + "[event <Task Name> /from <JUN 12 2024> /to <JUN 12 2024>] adds an Event task to the task list\n" 
            + "[mark <Task Number>] marks task as done\n" 
            + "[unmark <Task Number>] marks task as not done\n" 
            + "[delete <Task Number>] deletes the task from the task list\n" 
            + "[tag <Task Number> <Tag>] tags the task as the specified tag\n"
            + "[untag <Task Number> <Tag>] removes the specified tag from the task\n" 
            + "[delete <Task Number>] removes the task from the task list\n" 
            + "[filter <Tag>] shows the tasks that have the tag\n" 
            + "[find <Task Name>] shows the tasks that have matching name\n" 
            + "[bye] exits the program";
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return always false, as this command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof PrintListCommand;
    }
}
