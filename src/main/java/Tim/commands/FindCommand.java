package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.Task;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Tim.exception.ErrorMessages.MESSAGE_NO_MATCH_FOUND;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";

    Path filePath;
    String input;

    /**
     * Creates a FindCommand object to find tasks.
     * @param input
     * @param filePath
     */
    public FindCommand(String input, Path filePath) {
        this.filePath = filePath;
        this.input = input;
    }

    /**
     * Check for tasks that have matching string.
     * @param taskList TaskList containing all tasks
     * @return String containing matched tasks.
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException {
        Pattern p = Pattern.compile(input);
        TaskList matchedTasks = new TaskList();
        for (Task task : taskList) {
            String taskMsg = task.toString();
            Matcher m = p.matcher(taskMsg);
            if (m.find()) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.isEmpty()) {
            throw new TimException(MESSAGE_NO_MATCH_FOUND);
        }
        return GUI.showMatchedTasks(matchedTasks);
    }
}
