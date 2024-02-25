package command;

import java.util.LinkedList;
import java.util.StringTokenizer;

import task.Task;
import task.TaskList;

public class FindCommand extends Command {
    private TaskList taskList;
    private String keyword;

    public FindCommand(TaskList taskList, StringTokenizer st) {
        this.taskList = taskList;
        keyword = st.nextToken();
    }

    @Override
    public void execute() {
        System.out.println("Here are the matching tasks in your list:");
        LinkedList<Task> tl = taskList.getList();
        int counter = 1;
        for (Task t : tl) {
            if (t.hasKeyword(keyword)) {
                System.out.println(counter + ". " + t.toString());
                counter++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
