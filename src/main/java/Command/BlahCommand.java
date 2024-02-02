package command;


import ping.TaskList;
import ping.UI;


public class BlahCommand extends Command {

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        ui.respondBlah();
        return null;
    }
}
