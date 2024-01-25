public class Deadlines extends Task{
    String deadlineDate;
    public Deadlines(String nameOfTask, String deadlineDate){
        super(nameOfTask);
        this.deadlineDate = deadlineDate;
    }
    @Override
    public String toString(){
        if(status){
            return "[D][X] " + nameOfTask + " " + "(" + deadlineDate + ")";
        }
        else{
            return "[D][ ] " + nameOfTask + " " + "(" + deadlineDate + ")";
        }
    }
}
