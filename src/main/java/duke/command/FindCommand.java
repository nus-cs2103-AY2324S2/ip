package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class FindCommand extends Command{

    private String keyword;
    private int count;

    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.announceFinding();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task_now = tasks.getTask(i);
            String counter = count + ". ";
            if (task_now.findingKeyword(keyword)) {
                System.out.print(counter);
                System.out.println(task_now.toString());
                count+=1;
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
