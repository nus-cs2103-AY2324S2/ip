public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // List items in to-do list
        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        if (tasks.isEmpty()) {
            response = new StringBuilder("Your list is empty!");
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                response.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            } else {
                response.append(i + 1).append(".").append(tasks.get(i).toString());
            }
        }
        ui.sendMessage(response.toString());
    }
}
