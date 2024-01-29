import java.util.ArrayList;

public class MarkCommand extends Command {
    private final ArrayList<String> argsList;

    public MarkCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        // This means that the user has not supplied any number with the command
        if (argsList.size() == 1) {
            throw new NollidException("Please enter the task you wish to mark as done!\n" + "Usage: mark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(argsList.get(1));
                tasks.setDone(taskIndex, true);

                String response = "Good job! I've marked this task as done: \n"
                        + "\t " + tasks.get(taskIndex - 1).toString();

                ui.sendMessage(response);
                storage.update(tasks);
            } catch (NumberFormatException e) {
                throw new NollidException("Please enter a number for the mark command.\n" + "Usage: mark [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new NollidException("Are you sure that's a valid task number? (Tip: use 'list' to check the "
                        + "number of your task!)\n" + "Usage: mark [task number]");
            }
        }
    }
}
