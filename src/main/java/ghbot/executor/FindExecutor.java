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
     * Prints all the tasks that matches the description.
     */
    @Override
    public String execute() {
        List<String> matchedLst = new ArrayList<>();
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            String[] words = this.taskList.getTask(i).toString().split(" ");
            for (int j = 0; j < words.length; j++) {
                if (words[j].toLowerCase().startsWith(this.description)) {
                    matchedLst.add(this.taskList.getTask(i).toString());
                    break;
                }
            }
        }
        if (matchedLst.size() > 0) {
            this.executeStr = "Here are the list of tasks that matches the keyword:";
            for (int i = 0; i < matchedLst.size(); i++) {
                this.executeStr = this.executeStr + "\n" + (i + 1) + "." + matchedLst.get(i);
            }
            return this.executeStr;
        } else {
            return "Sorry! No match found!";
        }
    }
}
