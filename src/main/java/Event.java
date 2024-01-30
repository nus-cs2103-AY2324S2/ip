
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//public class Event extends Task{
//    protected String from;
//    protected String to;
//
//    public Event(String description, String from, String to) {
//        super(description);
//        this.from = from;
//        this.to = to;
//    }
//
//    @Override
//    public String toString() {
//        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
//    }

    public class Event extends Task {
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
            super(description);
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            String formattedStartTime = startTime.format(formatter);
            String formattedEndTime = endTime.format(formatter);

            return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        }
}
