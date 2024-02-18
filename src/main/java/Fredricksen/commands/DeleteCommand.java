package Fredricksen.commands;

import Fredricksen.commands.Command;
import Fredricksen.tasks.TaskList;
import Fredricksen.tasks.taskType.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] fullCommand, TaskList tasks) {
        super(fullCommand, tasks);
    }
    @Override
    public String execute() {
        String single = tasks.size() <= 1 ? "task" : "Fredricksen/tasks";
        try {
            Task currTask = tasks.getTask(Integer.parseInt(this.fullCommand[1]) - 1);
            tasks.deleteTask(Integer.parseInt(this.fullCommand[1]) - 1);
            return "Noted. I've removed this task:\n"
                    + "    " + currTask + "\n"
                    + "Now you have " + tasks.size() + " " + single + " in the task list.";
        } catch (IndexOutOfBoundsException err) {
            return "You only have "
                    + tasks.size() + " " + single
                    + " currently. Type \"tasks\" to view all your current "
                    + single;
        } catch (NumberFormatException err) {
            if (this.fullCommand[1].equalsIgnoreCase("all")) {
                tasks.clearList();
            } else {
                return "Please enter a valid input";
            }
            return "Clearing all current tasks in the list";
        }
    }
}
