package chingu.command;

import chingu.Storage;
import chingu.task.Task;
import chingu.task.TaskList;
import chingu.Ui;

public class FindCommand extends Command{

    private String keyword;
    private int count;

    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String Response = ui.announceFinding();
        for (int i = 0; i < tasks.getSizeNumber(); i++) {
            Task task_now = tasks.getTask(i);
            String counter = count + ". ";
            if (task_now.findingKeyword(keyword)) {
                Response = Response + counter + task_now.toString() + "\n";
                count+=1;
            }
        }
        return Response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
