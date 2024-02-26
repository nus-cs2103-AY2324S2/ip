package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.command.Command;
import duke.task.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Findcommand used for searching tasks by given string in task list.
 */
public class FindCommand extends Command {
    private String searchString = "";

    /**
     * Instantiates a new Find command.
     */
    public FindCommand(){

    }

    /**
     * Instantiates a new Find command.
     *
     * @param searchStr the query string
     */
    public FindCommand(String searchStr) {
        this.searchString = searchStr;
    }


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
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
            sb2.append(sb.toString());
        }
        ui.setCommandOutput(sb2.toString());
    }
}
