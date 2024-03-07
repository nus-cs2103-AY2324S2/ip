package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Find command.
 */
public class FindCommand extends Command{
    private String searchString = "";

    /**
     * Instantiates a new Find command.
     */
    public FindCommand(){

    }

    /**
     * Instantiates a new Find command.
     *
     * @param searchStr the search str
     */
    public FindCommand(String searchStr) {
        assert searchStr != null;
        assert !searchStr.equals("");

        this.searchString = searchStr;
    }

    /**
     * Finds the list of tasks in task list matching searchStr.
     *
     * @param taskList the task list
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> results = new ArrayList<>();
        Pattern pat = Pattern.compile(this.searchString, Pattern.CASE_INSENSITIVE);
        for (Task task : taskList.getTaskList()) {
            String taskdesc = task.getTaskName();
            Matcher matcher = pat.matcher(taskdesc);
            if (matcher.find()) {
                sb.append("\n").append(task.getStringRepresentation());
            }
        }
        StringBuilder sb2 = new StringBuilder();
        if (sb.toString().isBlank() || sb.toString().isEmpty()) {
            sb2.append("No matching tasks found in the list.");
        }else {
            sb2.append("Here are the matching tasks in your list:");
            sb2.append(sb);
        }
        return sb2.toString();
    }
}
