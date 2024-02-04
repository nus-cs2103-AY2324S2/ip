public class Event extends Task{
    private String startDate;
    private String endDate;
    public Event(String desc, String startDate, String endDate){
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Event(String desc, boolean isDone, String startDate, String endDate){
        super(desc, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String saveStorage(){
        String details = super.saveStorage();
        details = "E|" + details + startDate + endDate;
        return details;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDate + endDate + ")";
    }
}
