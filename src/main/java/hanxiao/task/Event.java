package hanxiao.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import hanxiao.exception.HanxiaoException;
import hanxiao.exception.TimeFormatException;
import hanxiao.exception.TimeInconsistException;
import hanxiao.exception.WrongUsageException;

/**
 * Class for task start with event
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor
     *
     * @param descrip the dicription of the task.
     * @param from from field.
     * @param to to field.
     */
    public Event(String descrip, LocalDate from, LocalDate to) {
        super(descrip);
        assert to.isAfter(from) || to.isEqual(from) : "to cannot before from";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor
     *
     * @param descrip description.
     * @param tags tags.
     * @param from from.
     * @param to to.
     */
    public Event(String descrip, ArrayList<String> tags, LocalDate from, LocalDate to) {
        super(descrip, tags);
        assert to.isAfter(from) || to.isEqual(from) : "to cannot before from";
        this.from = from;
        this.to = to;
    }

    /**
     * Override the abstract class
     *
     * @return T.
     */
    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    /**
     * Override the abstract class
     *
     * @return event.
     */
    @Override
    public String getTaskType() {
        return "event";
    }

    /**
     * Override the toString method
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s(from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    /**
     * return from
     *
     * @return from.
     */
    public String getFrom() {
        return this.from.toString();
    }

    /**
     * return to
     *
     * @return to.
     */
    public String getTo() {
        return this.to.toString();
    }

    /**
     * Whether we have to start doing it
     *
     * @param current current time.
     * @return yes/no.
     */
    public boolean isTimeForStart(LocalDate current) {
        if (isDone) {
            return false;
        }
        return current.compareTo(to) <= 0 && current.compareTo(from) >= 0;
    }

    /**
     * Getter for from.
     *
     * @return from.
     */
    public LocalDate getFromTime() {
        return this.from;
    }

    /**
     * Getter for to
     *
     * @return to.
     */
    public LocalDate getToTime() {
        return this.to;
    }

    /**
     * Compare the task
     *
     * @param obj a task.
     * @return whether to task are same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event temp = (Event) obj;
            boolean equalDescribe = this.description.equals(temp.getDescription());
            boolean equalFrom = this.from.isEqual(temp.from);
            boolean equalTo = this.to.isEqual(temp.to);
            return equalDescribe && equalFrom && equalTo;
        }
        return false;
    }

    /**
     * Compare the task
     *
     * @param otherTask the other task.
     * @return which task come first.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return -1;
        }
        if (otherTask instanceof Deadline) {
            Deadline temp = (Deadline) otherTask;
            return this.from.compareTo(temp.getByTime());
        }
        Event temp = (Event) otherTask;
        return this.from.compareTo(temp.getFromTime());
    }

    /**
     * Update a task
     *
     * @param updateField the field to update.
     * @param updateInfo the value to update.
     * @throws WrongUsageException wrong format.
     */
    @Override
    public void updateTask(String updateField, String updateInfo) throws HanxiaoException {
        if (updateField.equals("/des")) {
            this.description = updateInfo;
        } else if (updateField.equals("/from")) {
            String timeInfo = updateInfo;
            timeInfo = changeWordToDate(timeInfo);
            if (!checkTimeForm(timeInfo)) {
                throw new TimeFormatException();
            }
            LocalDate newFrom = LocalDate.parse(timeInfo);
            if (newFrom.isAfter(this.to)) {
                throw new TimeInconsistException();
            }
            this.from = newFrom;
        } else if (updateField.equals("/to")) {
            String timeInfo = updateInfo;
            timeInfo = changeWordToDate(timeInfo);
            if (!checkTimeForm(timeInfo)) {
                throw new TimeFormatException();
            }
            LocalDate newTo = LocalDate.parse(timeInfo);
            if (newTo.isBefore(this.from)) {
                throw new TimeInconsistException();
            }
            this.to = newTo;
        } else {
            throw new WrongUsageException("use /from or /to to identify update.");
        }
    }

    /**
     * Time parser
     *
     * @param time time in sting.
     * @return time in string.
     */
    private String changeWordToDate(String time) {
        LocalDate currentTime = LocalDate.now();
        int todayDay = currentTime.getDayOfWeek().getValue();
        int timeDay = 0;
        if (time.equals("Mon") || time.equals("Monday")) {
            timeDay = 1;
        } else if (time.equals("Tues") || time.equals("Tuesday")) {
            timeDay = 2;
        } else if (time.equals("Wed") || time.equals("Wednesday")) {
            timeDay = 3;
        } else if (time.equals("Thurs") || time.equals("Thursday")) {
            timeDay = 4;
        } else if (time.equals("Fri") || time.equals("Friday")) {
            timeDay = 5;
        } else if (time.equals("Sat") || time.equals("Saturday")) {
            timeDay = 6;
        } else if (time.equals("Sun") || time.equals("Sunday")) {
            timeDay = 7;
        } else if (time.equals("Today") || time.equals("today")) {
            return currentTime.toString();
        } else if (time.equals("Tomorrow") || time.equals("tomorrow")) {
            return currentTime.plusDays(1).toString();
        } else {
            return time;
        }
        if (timeDay <= todayDay) {
            int minusDay = todayDay - timeDay;
            return currentTime.plusWeeks(1).minusDays(minusDay).toString();
        } else {
            int plusDay = timeDay - todayDay;
            return currentTime.plusDays(plusDay).toString();
        }
    }

    /**
     * Check time format
     *
     * @param time time in string.
     * @return whether it is in correct format.
     */
    private boolean checkTimeForm(String time) {
        try {
            LocalDate.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
