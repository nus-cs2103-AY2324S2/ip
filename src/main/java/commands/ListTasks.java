package commands;

import objects.TaskList;
import view.EncaseLines;

public class ListTasks implements Command {
    private final TaskList tasks;
    public ListTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        StringBuilder output = new StringBuilder();

        if (tasks.isEmpty()) {
            EncaseLines.display("List is empty!");
        } else {

            for (int i = 0; i < tasks.size(); i++) {
                output.append(String.format("%d. %s", i + 1, tasks.get(i)));

                if (i < tasks.size() - 1) {
                    output.append("\n");
                }
            }

            EncaseLines.display(output.toString());
        }
    }
}
