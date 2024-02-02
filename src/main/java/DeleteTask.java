public class DeleteTask extends Command{
    private int index;
    public DeleteTask(int index){
        this.index = index;
    }
    @Override
    public void run(TaskList taskList){
        taskList.deleteList(this.index);
    }
}
