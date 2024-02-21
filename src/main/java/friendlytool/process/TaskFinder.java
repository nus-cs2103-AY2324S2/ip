package friendlytool.process;

import friendlytool.command.Parser;
import friendlytool.task.Task;

/**
 * A class that helps finding tasks using keyword.
 */
public class TaskFinder {
    /**
     * Finds the task based on the given keyword from a list.
     *
     * @param tasks given task
     * @param input keyword
     * @return found task
     * @throws FtException if keyword is not found
     */
    public static String findTask(TaskList tasks, String input) throws FtException {
        int counter = 0;
        String keyword = Parser.parseKeyword(input);
        UI.prepareMatchingMsg();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                counter++;
                UI.prepareFindTaskMsg(counter, task);
            }
        }
        return UI.getFound();
    }
}
