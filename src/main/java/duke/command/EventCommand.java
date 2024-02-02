package duke.command;

import duke.task.TaskList;
import duke.task.Event;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MissingEventException;
import duke.exception.SaveStorageException;

/**
 * The class representing the creation of deadline task command.
 * */
public class EventCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    String[] commandList;

    public EventCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws MissingEventException {
        String response = "";
        if (this.commandList.length <= 1) {
            throw new MissingEventException();
        }
        Event currentEvent = new Event(commandList[1], commandList[2], commandList[3]);
        taskList.add(currentEvent);

        response += "Got it. I've added this task:\n  " + currentEvent;
        response += "\nNow you have " + taskList.size() + " tasks in the list.";
        System.out.println("Got it. I've added this task:\n  " + currentEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
