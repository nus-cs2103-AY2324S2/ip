package ghbot.executor;

import java.util.ArrayList;
import java.util.List;

public class FindExecutor extends Executor {
    private String description;
    public FindExecutor(String description) {
        this.description = description;
    }
    @Override
    public void execute() {
        List<String> matchedLst = new ArrayList<>();
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            String[] words = this.taskList.getTask(i).toString().split(" ");
            for (int j = 0; j < words.length; j++) {
                if (this.description.equalsIgnoreCase(words[j])) {
                    matchedLst.add(this.taskList.getTask(i).toString());
                    break;
                }
            }
        }
        if (matchedLst.size() > 0) {
            System.out.println("Here are the list of tasks that matches the keyword:");
            for (int i = 0; i < matchedLst.size(); i++) {
                System.out.println(i + 1 + "." + matchedLst.get(i));
            }
        } else {
            System.out.println("Sorry! No match found!");
        }
    }
}
