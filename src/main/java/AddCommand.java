import jdk.jshell.spi.ExecutionControl;

public class AddCommand extends Command{
    private Task task;
    public AddCommand(){
    }

    public AddCommand(Task task){
        this.task = task;
    }
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        taskList.add(this.task);
        storage.Store(taskList.toString());
        int count = taskList.getTaskList().size();
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append("\n").append(this.task.printOutput());
        sb.append("\n").append("Now you have "+count+" tasks in the list");
        ui.setCommandOutput(sb.toString());
    }
}
