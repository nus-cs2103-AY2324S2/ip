package tasks;

import cro.*;
import java.time.LocalDateTime;
import java.util.List;

public class Event extends Task {

    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    public Event(List<String> splitStr) throws CroException {
        int isDone = Integer.parseInt(splitStr.get(1));
        int fromIndex = splitStr.indexOf("/from");
        int toIndex = splitStr.indexOf("/to");
        String description = String.join(" ", splitStr.subList(2, fromIndex));
        try {
            this.fromTime = LocalDateTime.parse(splitStr.get(fromIndex + 1));
            this.toTime = LocalDateTime.parse(splitStr.get(toIndex + 1));
        } catch (Exception e) {
            throw new CroException("start or end time must be in the format YYYY MM DD HH MM");
        }
        if (description.equals("")) {
            throw new CroException("description, start or end cannot be empty!");
        }
        this.description = description;
        if (isDone == 1) {
            this.markAsDone();
        }
    }
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(),
                this.description, this.fromTime.format(dateFormat), this.toTime.format(dateFormat));
    }
    @Override
    public String getSaveLine() {
        return "E " + (this.isDone ? "1 " : "0 ")
                + this.description + " /from "
                + this.fromTime.toString() + " /to "
                + this.toTime.toString() + "\n";
    }

}
