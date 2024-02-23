package ghbot.executor;

import java.util.ArrayList;
import java.util.List;

/**
 * FindExecutor Class.
 * Executes "Find" instruction.
 */
public class FindExecutor extends Executor {
    private String description;
    private String executeStr;

    /**
     * FindExecutor Constructor.
     * @param description A description of the word the user is finding.
     */
    public FindExecutor(String description) {
        this.description = description.toLowerCase();
        this.executeStr = "";
    }

    /**
     * Returns all the tasks that matches the description.
     * @return A string containing all the tasks that matches the description.
     */
    @Override
    public String execute() {
        List<String> matchedList = new ArrayList<>();
        for (int i = 0; i < taskList.taskSize(); i++) {
            String[] words = taskList.getTask(i).toString().split(" ");
            for (int j = 0; j < words.length; j++) {
                if (words[j].toLowerCase().contains(this.description)) {
                    matchedList.add(taskList.getTask(i).toString());
                    break;
                }
            }
        }
        if (matchedList.size() > 0) {
            this.executeStr = "Here are the list of tasks that matches with your description:";
            for (int i = 0; i < matchedList.size(); i++) {
                this.executeStr = this.executeStr + "\n" + (i + 1) + "." + matchedList.get(i);
            }
            return this.executeStr;
        } else {
            return "Sorry! No match found!";
        }
    }
}
