package tofu.command;

import tofu.task.*;
import tofu.TofuException;
import tofu.ui.Ui;
import tofu.task.TaskList;

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
     * Executes the appropriate command based on the string commandWord, adds the corresponding Task to the TaskList,
     * and display the UI message.
     *
     * @param tasks The TaskList to which the Task will be added.
     * @param ui The UI that will display the message.
     * @return A string representing the UI message and the new Task that has been added to the TaskList.
     * @throws TofuException If the format of the input string does not match the expected format
     * for the identified command word.
     */
    public String execute(TaskList tasks, Ui ui) throws TofuException {
        Task task;
        if (commandWord.equals("todo")) {
            task = new ToDo(description.trim());
            tasks.add(task);
        } else if (commandWord.equals("deadline")) {
            String[] splitBy = description.split("/by");
            if (splitBy.length < 2) {
                throw new TofuException("Deadline /by cannot be empty!");
            }
            description = splitBy[0];
            String by = splitBy[1];
            // Create Task and add to list
            task = new Deadline(description.trim(), by.trim());
            tasks.add(task);
        } else {
            String[] splitFrom = description.split("/from");
            if (splitFrom.length < 2) {
                throw new TofuException("Event /from cannot be empty!");
            }
            description = splitFrom[0];
            String[] splitTo = splitFrom[1].split("/to");
            if (splitTo.length < 2) {
                throw new TofuException("Event /to cannot be empty!");
            }
            String from = splitTo[0];
            String to = splitTo[1];
            // Create Task and add to list
            task = new Event(description.trim(), from.trim(), to.trim());
            tasks.add(task);
        }
        return ui.addMessage(task.toString(), tasks.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof AddCommand command) {
            return commandWord.equals(command.commandWord) && description.equals(command.description);
        } else {
            return false;
        }
    }

    public boolean isExit() {
        return false;
    }
}
