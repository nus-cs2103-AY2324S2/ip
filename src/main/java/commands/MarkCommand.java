package commands;

import DukeException.DukeException;
import tasks.TaskList;

public class MarkCommand extends Command {
    private final String details;

    MarkCommand(String details) {
        this.details = details;
    }
    public void execute(TaskList tasks) throws DukeException {
        if(details.length() < 1) { throw new DukeException(
                "Please enter the tasks number that you want to mark as incomplete: ex. mark 2"); }

        try {
            int i = Integer.parseInt(details) - 1;
            tasks.get(i).markAsUndone();
            System.out.println("OK, I've marked this tasks as not done yet");
            System.out.println(tasks.get(i).toString());
        } catch(Exception e) {
            throw new DukeException("Please enter the valid tasks number");
        }
    }
}
