import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    private LocalDateTime deadline;
    private String deadlineString;

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getDeadlineString() {
        return deadlineString;
    }

    public void setDeadlineString(String deadlineString) {
        this.deadlineString = deadlineString;
    }

    public Deadline(String description, boolean hasDone, LocalDateTime deadline) {
        this.deadline = deadline;
        this.deadlineString = deadline.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public static Deadline buildDeadLine(String input) throws InvalidInputException {
        String example = "E.g. deadline SU All Courses /by 2022-01-01 18:00";
        try {
            String[] splitedInput = input.split("/");
            String taskDescription = splitedInput[0];
            taskDescription = taskDescription.substring(8).trim();
            String[] tmp = splitedInput[1].split(" ");
            LocalDateTime deadline = LocalDateTime.parse(tmp[1] + " " + tmp[2],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            return new Deadline(taskDescription, false, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Bro, it seems like you have entered wrong input for deadline.\n" +
                    example);
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineString + ")";
    }
}
