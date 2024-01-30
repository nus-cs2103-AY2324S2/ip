public class MarkCommand extends Command{

    private String input;

    public MarkCommand(String userInput) {
        this.input = userInput;
    }

    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String splitInput[] = input.split(" ");
        if (tasks.getTasks().size() == 0) {
            throw new DukeException("No task at the moment.");
        } else if (splitInput.length < 2) {
            throw new DukeException("Please select the task.");
        }
        int choiceMark;
        try {
            choiceMark = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer value.");
        }
        if (choiceMark <= tasks.getTasks().size() && choiceMark > 0) {
            tasks.markTask(choiceMark - 1);
            storage.writeArrayListToFile(tasks.getTasks(), true);
            ui.printAnyStatement("Nice! I've marked this task as done:");
            ui.printAnyStatement(tasks.getTasks().get(choiceMark - 1).toString());
        } else {
            throw new DukeException("Invalid choice.");
        }
    }
}
