public class DeleteTask extends Command{
    private int index;
    public DeleteTask(Parser.Cmd type, int index){
        super(type);
        this.index = index;
    }
    @Override
    public void run(TaskList taskList){
        taskList.deleteList(this.index);
    }
}
