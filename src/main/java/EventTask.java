public class EventTask extends Task {
    
    private String startTiming;
    private String endTiming;

    EventTask(String description, String startTiming, String endTiming) {
        super(description);
        this.startTiming = startTiming;
        this.endTiming = endTiming;
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        return "[E]" + status + " " + super.getDescription() + " (from: " + this.startTiming + " to: " + this.endTiming + ")";
    }

}
