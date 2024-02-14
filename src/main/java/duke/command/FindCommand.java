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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.announceFinding();
        String Response = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task_now = tasks.getTask(i);
            String counter = count + ". ";
            if (task_now.findingKeyword(keyword)) {
                Response = Response + counter + task_now.toString() + "\n";
//                System.out.print(counter);
//                System.out.println(task_now.toString());
                count+=1;
            }
        }
        return Response;
    }

    public boolean isExit() {
        return false;
    }
}
