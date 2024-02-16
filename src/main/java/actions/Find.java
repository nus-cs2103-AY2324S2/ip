package actions;

import exceptionhandling.DukeException;
import java.util.ArrayList;

import tasks.Task;
import ui.Duke;

public class Find implements Action {
    private String search;

    public Find(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length <= 1) {
            throw new DukeException("Please include search details");
        }
        String search = splitCommand[1];
        this.search = search;
    }

    @Override
    public String execute(Duke bot) {
        assert(bot != null);
        assert(bot.getTaskList() != null);
        ArrayList<Task> list = bot.getTaskList().getList();
        ArrayList<Task> matches = new ArrayList<>();

        for (Task t : list) {
            if (t.toString().contains(this.search)) {
                matches.add(t);
            }
        }
        if (matches.size() > 0) {
            StringBuilder stringOfMatches = new StringBuilder("Here are the matching tasks in your list:\n");
            int index = 1;
            for (Task task : matches) {
                stringOfMatches.append(String.format("%d. [%s] [%s] %s\n",
                        index, task.getTypeIcon(), task.getStatusIcon(), task.toString()));
                index++;
            }
            return stringOfMatches.toString();
        } else {
            return ("No matches found!");
        }
    }
}
