package ghbot.executor;

import java.util.ArrayList;
import java.util.List;

/**
 * FindExecutor Class.
 * Executes "Find" command.
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
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            String[] words = this.taskList.getTask(i).toString().split(" ");
            for (int j = 0; j < words.length; j++) {
                if (words[j].toLowerCase().startsWith(this.description)) {
                    matchedList.add(this.taskList.getTask(i).toString());
                    break;
                }
            }
        }
        if (matchedList.size() > 0) {
            this.executeStr = "Here are the list of tasks that matches the keyword:";
            for (int i = 0; i < matchedList.size(); i++) {
                this.executeStr = this.executeStr + "\n" + (i + 1) + "." + matchedList.get(i);
            }
            return this.executeStr;
        } else {
            return "Sorry! No match found!";
        }
    }
}
