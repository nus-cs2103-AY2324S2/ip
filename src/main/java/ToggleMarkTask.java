public class ToggleMarkTask extends Command{
    private boolean isMarking;
    private int index;

    public ToggleMarkTask(Parser.Cmd type, boolean isMarking, int index){
        super(type);
        this.isMarking = isMarking;
        this.index = index;
    }
    @Override
    public void run(TaskList taskList){
        if (this.isMarking){
            taskList.markList(this.index);
        } else {
            taskList.unmarkList(this.index);
        }
    }
}
