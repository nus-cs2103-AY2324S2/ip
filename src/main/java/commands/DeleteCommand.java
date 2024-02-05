package commands;

import DukeException.DukeException;
import tasks.TaskList;
import tasks.Task;

public class DeleteCommand {
    public static void execute(String details, TaskList tasks) throws DukeException {
        if (details.length() < 1) {
            throw new DukeException("Please enter the tasks number that you want to delete: ex. delete 2");
        }

        try {
            int index = Integer.parseInt(details) - 1;
            Task task = tasks.get(index);
            System.out.println("Noted. I've removed this tasks:");
            System.out.println(task);
            tasks.remove(index);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch(Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }
    }
}
