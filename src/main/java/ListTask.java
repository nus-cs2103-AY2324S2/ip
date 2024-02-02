public class ListTask extends Command{
    public ListTask(Parser.Cmd type) {
        super(type);
    }
    @Override
    public void run(TaskList taskList){
        OutputMessage.displayFullList(taskList);
    }
}
