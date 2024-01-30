public class DeadLineTask extends Task{
    private final String  deadline;
    public DeadLineTask(String dl,String task){
        super(task);
        deadline = dl;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline+")";
    }
    @Override
    public String logString(){return 'D' + super.logString() + '|' + deadline;}
}
