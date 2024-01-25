public class DeadLineTask extends Task{
    private String deadline;
    public DeadLineTask(String dl,String task){
        super(task);
        deadline = dl;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline+")";
    }
}
