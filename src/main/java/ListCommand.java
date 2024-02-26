public class ListCommand extends Command{
    public ListCommand(){
    }
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException{
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        String taskoutput = taskList.printOutput();
        sb.append(taskoutput);
        ui.setCommandOutput(sb.toString());
    }
}
