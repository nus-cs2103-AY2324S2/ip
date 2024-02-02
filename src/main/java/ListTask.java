public class ListTask extends Command{
    @Override
    public void run(TaskList taskList){
        taskList.showList();
    }
}
