//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//
//    public class Event extends Task {
//        private LocalDateTime startTime;
//        private LocalDateTime endTime;
//
//        public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
//            super(description);
//            this.startTime = startTime;
//            this.endTime = endTime;
//        }
//
//        public LocalDateTime getStartTime() {
//            return startTime;
//        }
//
//        public LocalDateTime getEndTime() {
//            return endTime;
//        }
//
//        @Override
//        public String toString() {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
//            String formattedStartTime = startTime.format(formatter);
//            String formattedEndTime = endTime.format(formatter);
//
//            return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
//        }
//}


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.startTime = LocalDateTime.parse(startTime, formatter);
            this.endTime = LocalDateTime.parse(endTime, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use: yyyy-MM-dd HHmm");
            // Handle the exception as needed, e.g., log it or inform the user
            throw e;
        }
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
