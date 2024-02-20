package duke.command;

import duke.task.*;
import duke.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;

public class AddCommand implements Command {

    private String commandWord;
    private String description;

    /**
     * Initializes a new AddCommand to add a task to the TaskList.
     *
     * @param commandWord the type of Task that will be added
     * @param description the full description of the Task that will be added
     */
    public AddCommand(String commandWord, String description) {
        this.commandWord = commandWord;
        this.description = description;
    }

    /**
     * Add the corresponding Task into the TaskList based on the command word
     * and display the UI message
     *
     * @param tasks the TaskList for which the Task will be added to
     * @param ui the UI that will be used to display the message
     * @return a String of the UI message and the new Task that will be added to the TaskList
     * @throws DukeException if format of the description does not match
     */
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        Task task;
        if (commandWord.equals("todo")) {
            task = new ToDo(description.trim());
            tasks.add(task);
        } else if (commandWord.equals("deadline")) {
            String[] splitBy = description.split("/by");
            if (splitBy.length < 2) {
                throw new DukeException("Deadline /by cannot be empty!");
            }
            description = splitBy[0];
            String by = splitBy[1];
            // Create Task and add to list
            task = new Deadline(description.trim(), by.trim());
            tasks.add(task);
        } else {
            String[] splitFrom = description.split("/from");
            if (splitFrom.length < 2) {
                throw new DukeException("Event /from cannot be empty!");
            }
            description = splitFrom[0];
            String[] splitTo = splitFrom[1].split("/to");
            if (splitTo.length < 2) {
                throw new DukeException("Event /to cannot be empty!");
            }
            String from = splitTo[0];
            String to = splitTo[1];
            // Create Task and add to list
            task = new Event(description.trim(), from.trim(), to.trim());
            tasks.add(task);
        }
        return ui.addMessage(task.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
