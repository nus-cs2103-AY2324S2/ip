public class Events extends Task{
    String startDate;
    String endDate;
    public Events(String nameOfTask, String startDate, String endDate){
        super(nameOfTask);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString(){
        if(status){
            return "[E][X] " + nameOfTask + " " + "(from: " + startDate + " to: " + endDate + ")";
        }
        else{
            return "[E][ ] " + nameOfTask + " " + "(from: " + startDate + " to: " + endDate + ")";
        }
    }
}
