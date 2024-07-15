package hanxiao.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import hanxiao.exception.HanxiaoException;
import hanxiao.exception.TimeFormatException;
import hanxiao.exception.WrongUsageException;

/**
 * Class for task start with deadline
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * constructor
     *
     * @param descrip the dicription of the task.
     * @param by by field.
     */
    public Deadline(String descrip, LocalDate by) {
        super(descrip);
        this.by = by;
    }

    /**
     * Constructor
     *
     * @param descrip the dicription of the task.
     * @param tags tags.
     * @param by by field.
     */
    public Deadline(String descrip, ArrayList<String> tags, LocalDate by) {
        super(descrip, tags);
        this.by = by;
    }

    /**
     * Override the abstract class
     *
     * @return T.
     */
    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    /**
     * Override the abstract class
     *
     * @return deadline.
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Override the toString method
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s(by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * return by
     *
     * @return return by.
     */
    public String getBy() {
        return this.by.toString();
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
        return current.compareTo(by) <= 0;
    }

    /**
     * Getter for by.
     *
     * @return by time.
     */
    public LocalDate getByTime() {
        return this.by;
    }

    /**
     * Compare the task
     *
     * @param obj a task.
     * @return whether to task are same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline temp = (Deadline) obj;
            boolean equalDescribe = this.description.equals(temp.getDescription());
            boolean equalBy = this.by.isEqual(temp.by);
            return equalDescribe && equalBy;
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
        if (otherTask instanceof Event) {
            Event temp = (Event) otherTask;
            return this.by.compareTo(temp.getFromTime());
        }
        Deadline temp = (Deadline) otherTask;
        return this.by.compareTo(temp.getByTime());
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
        } else if (updateField.equals("/by")) {
            String timeInfo = updateInfo;
            timeInfo = changeWordToDate(timeInfo);
            if (!checkTimeForm(timeInfo)) {
                throw new TimeFormatException();
            }
            this.by = LocalDate.parse(timeInfo);
        } else {
            throw new WrongUsageException("use /by to identify update.");
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
