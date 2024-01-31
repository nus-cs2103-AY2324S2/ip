public class Event extends Task{
    String startDate;
    String endDate;
    public Event(String name,String startDate,String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + "("+this.startDate + this.endDate+")";
    }
}
