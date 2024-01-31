public class Event extends Task{
    String startDate;
    String endDate;
    public Event(String name, String startDate, String endDate, boolean isDone) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[E]%s (from: %s to: %s)", super.toString(), startDate, endDate);
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s event %s /from %s /to %s", isDone, name, startDate, endDate);
        return str;
    }
}
