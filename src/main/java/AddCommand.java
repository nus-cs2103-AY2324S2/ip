public class AddCommand implements Command {

    private String commandWord;
    private String description;
    static String defaultString = "Got it. I've added this task:";

    AddCommand(String commandWord, String description) {
        this.commandWord = commandWord;
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task;
        String[] stringSplit;
        switch (commandWord) {
        case "todo":
            task = new ToDo(description.trim());
            tasks.add(task);
            ui.addMessage(task.toString(), tasks.size());
            break;
        case "deadline":
            stringSplit = description.split("/by");
            if (stringSplit.length < 2) {
                throw new DukeException("Deadline /by cannot be empty!");
            }
            description = stringSplit[0];
            String by = stringSplit[1];
            // Create Task and add to list
            task = new Deadline(description.trim(), by.trim());
            tasks.add(task);
            ui.addMessage(task.toString(), tasks.size());
            break;
        case "event":
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
            ui.addMessage(task.toString(), tasks.size());
            break;
        }
    }

    public boolean isExit() {
        return false;
    }
}
