package Command;
import TaskList.TaskList;
import UiRelated.Ui;
public class showListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui){
        String s = taskList.showLists();
        if (s.isEmpty()){
            ui.display("HASSNT:\n" + "NO TASKS IN TO DO LIST 🎉");
        }
        else {
            ui.display(s);
        }
    }
}
