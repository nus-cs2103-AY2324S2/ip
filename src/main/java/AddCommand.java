abstract class AddCommand extends Command {
    protected final String description;
    AddCommand(String description) {
        super("");
        this.description = description;
    }

    protected void respond(TaskList taskList, Task newTask) {
        String message =
                "Got it, I've added this task:\n  "
                + newTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        Ui.display(message);
    }
}