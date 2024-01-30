package task;

import duke.ProgramState;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String body) {
        super(body);
    }

    public String execute(TaskList list, ProgramState state) throws DukeException {
        String body = getBody();
        if (body.isEmpty()) {
            throw new InvalidTaskIndexException("The index of a task cannot be empty.",
                    "Sorry, but I don't know which task you want to mark as undone.");
        }
        int index = Integer.parseInt(body);
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
