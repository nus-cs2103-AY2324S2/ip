package task;

import duke.ProgramState;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String body) throws DukeException {
        super(body);
        try {
            this.index = Integer.parseInt(body);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("The index of a task must be an integer.",
                    "Sorry, but I don't know which task you want to mark as undone.");
        }
    }

    public String execute(TaskList list, ProgramState state) throws DukeException {
        if (index < 1 || index > list.size()) {
            throw new InvalidTaskIndexException(
                    "The index of a task cannot be less than 1 or greater than the number of tasks.",
                    "Sorry, but task number " + index + " does not exist. You only have " + list.size() + " tasks.");
        }
        list.unmarkTaskAsDone(index);
        String response = ("Undone: " + list.get(index - 1));
        state.setNormal();
        return response;
    }
}
