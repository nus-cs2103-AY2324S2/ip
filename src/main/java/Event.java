import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String startDateString;
    private String endDateString;

    public Event(String description, boolean hasDone, LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString =  startDate.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        this.endDateString = endDate.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public static Event buildEvent(String input) throws InvalidInputException {
        String example = "E.g. event Exam /from 2022-01-01 18:00 /to 2022-01-02 19:00";
        try {
            String[] formattedInput = input.split("/");
            String taskDescription = formattedInput[0].substring(5).trim();
            LocalDateTime startDate = LocalDateTime.parse(formattedInput[1].substring(4).trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime endDate = LocalDateTime.parse(formattedInput[2].substring(2).trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new Event(taskDescription, false, startDate, endDate);
        }
        catch (DateTimeParseException e) {
            throw new InvalidInputException("Bro, it seems that you have entered an incorrect format for event\n" +
                    example);
        }
    }


    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.startDateString + " to: " + this.endDateString + ")";
    }
}
