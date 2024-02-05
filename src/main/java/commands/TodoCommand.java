package commands;

import DukeException.DukeException;
import tasks.TaskList;
import tasks.Todo;

public class TodoCommand extends Command {
    private final String details;

   TodoCommand(String details) {
        this.details = details;
    }
    public void execute(TaskList tasks) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException("Please enter tasks description");
        }

        tasks.add(new Todo(details));
        System.out.println("Got it. I've added this tasks:");
        System.out.println("added: " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

}
