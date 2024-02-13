package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to find keyword.
 */
public class FindCommand implements Command {

    private String[] keywords;

    public FindCommand(String... input) {
        this.keywords = input;
    }

    /**
     * Searches through the list of tasks for matching keyword given in input.
     *
     * @param list Holds all tasks added.
     * @param ui Displays message of operation.
     * @param storage Handles IO operations.
     * @return String of response of chatbot.
     * @throws DukeException No thrown.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> arr = findTasks(list, keywords);
        return ui.showMatchedTasks(new TaskList(arr));
    }

    private ArrayList<Task> findTasks(TaskList list, String... keywords) {
        ArrayList<Task> arr = new ArrayList<>();
        for (String keyword : keywords) {
            for (Task t : list.getList()) {
                if (t.getTask().contains(keyword)) {
                    if (!arr.contains(t)) {
                        arr.add(t);
                    }
                }
            }
        }
        return arr;
    }

    @Override
    public boolean equals(Object a) {
        FindCommand fc = (FindCommand) a;
        if (fc.keywords.length != this.keywords.length) {
            return false;
        }
        for (int i = 1; i < fc.keywords.length; i++) {
            if (!this.keywords[i].equals(fc.keywords[i])) {
                return false;
            }
        }
        return true;
    }
}
