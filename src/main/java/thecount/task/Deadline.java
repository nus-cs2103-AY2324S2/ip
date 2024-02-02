package thecount.task;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadlineTime;

    public Deadline (String description, String deadlineTime) throws DateTimeParseException {
        super(description);
        try {
            this.deadlineTime = convertStringToDate(deadlineTime);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    private LocalDate convertStringToDate(String deadlineTime) throws DateTimeParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(deadlineTime, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    private String convertDateToString(LocalDate deadlineTime) {
        return deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        replyToUser.displayMessage();
    }

    private void handleException(Exception e) {
        Reply errorMsg = new Reply(e.getMessage());
        errorMsg.displayMessage();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    // To write to file
    public String getDesc() {
        return super.getDesc() + " | " + this.deadlineTime;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateToString(this.deadlineTime) + ")";
    }
}
