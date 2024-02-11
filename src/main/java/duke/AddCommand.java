package duke;

public class AddCommand implements Command {

    private String commandWord;
    private String description;

    AddCommand(String commandWord, String description) {
        this.commandWord = commandWord;
        this.description = description;
    }

    /**
     * Add the corresponding Task into the TaskList based on the command word
     * and display the UI message
     *
     * @param tasks the TaskList for which the Task will be added to
     * @param ui the UI that will be used to display the message
     * @throws DukeException if format of the description does not match.
     */
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        Task task;
        String[] stringSplit;
        if (commandWord.equals("todo")) {
            task = new ToDo(description.trim());
            tasks.add(task);
        } else if (commandWord.equals("deadline")) {
            stringSplit = description.split("/by");
            if (stringSplit.length < 2) {
                throw new DukeException("Deadline /by cannot be empty!");
            }
            description = stringSplit[0];
            String by = stringSplit[1];
            // Create Task and add to list
            task = new Deadline(description.trim(), by.trim());
            tasks.add(task);
        } else {
            stringSplit = description.split("/from");
            if (stringSplit.length < 2) {
                throw new DukeException("Event /from cannot be empty!");
            }
            description = stringSplit[0];
            stringSplit = stringSplit[1].split("/to");
            if (stringSplit.length < 2) {
                throw new DukeException("Event /to cannot be empty!");
            }
            String from = stringSplit[0];
            String to = stringSplit[1];
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
