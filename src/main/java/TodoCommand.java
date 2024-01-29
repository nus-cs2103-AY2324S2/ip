import java.util.ArrayList;

public class TodoCommand extends Command {
    private final ArrayList<String> argsList;

    public TodoCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        if (argsList.size() == 1) {
            throw new NollidException("Todo description cannot be empty!\n"
                    + "Usage: todo [task description]");
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < argsList.size(); i++) {
            if (i != argsList.size() - 1) {
                taskDescription.append(argsList.get(i)).append(" ");
            } else {
                taskDescription.append(argsList.get(i));
            }
        }

        ToDo task = new ToDo(taskDescription.toString());

        tasks.add(task);

        String message = "Alright, added:\n" + "\t" + task + "\n";
        message += tasks.summary();
        ui.sendMessage(message);
        storage.update(tasks);
    }
}
