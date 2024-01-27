public class Event extends Task {
    private final String startTime;
    private final String endTime;

    Event(String content, String startTime, String endTime) {
        super(content);
        if (startTime.isEmpty() || endTime.isEmpty()) { //handling the case where event does not get valid dates.
            this.startTime = String.valueOf(MamtaException.invalidDates());
            this.endTime = "";
        } else {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    Event(boolean isComplete, String content, String startTime, String endTime) {
        super(isComplete, content);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Event markDone() {
        return new Event(true, this.content, this.startTime, this.endTime);
    }
    @Override
    public Event unmarkTask() {
        return new Event(false, this.content, this.startTime, this.endTime);
    }

    public String toString() {
        if (this.isComplete) {
            return String.format("[D][X] %s(by: %sto: %s)", this.content, this.startTime, this.endTime);
        } else {
            return String.format("[D][ ] %s(by: %sto: %s)", this.content, this.startTime, this.endTime);
        }
    }

}
