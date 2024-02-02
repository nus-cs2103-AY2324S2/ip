public class ToggleMarkTask extends Command{
    private boolean isMarking;
    private int index;

    public ToggleMarkTask(boolean isMarking, int index){
        this.isMarking = isMarking;
        this.index = index;
    }
    @Override
    public void run(TaskList taskList){
        if (this.isMarking){
            taskList.markList(index);
        } else {
            taskList.unmarkList(index);
        }
    }
}
