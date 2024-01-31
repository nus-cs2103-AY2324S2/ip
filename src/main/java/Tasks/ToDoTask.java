package Tasks;

public class ToDoTask extends Task{

    public ToDoTask(String task){
        super(task);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public String logString(){return 'T' + super.logString();}
}