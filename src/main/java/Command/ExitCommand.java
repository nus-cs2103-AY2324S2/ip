package command;
import ping.TaskList;
import ping.UI;
public class ExitCommand extends Command {
    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.goodBye();

        return tasks;
    }

    @Override
    public boolean isRunning() {
        return false;
    }




}
