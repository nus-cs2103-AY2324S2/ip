public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate){
        super.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString(){
        return this.description + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
