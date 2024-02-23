package ukecat;

import java.time.LocalDate;


/**
 * Represents a recurring task that inherits from the abstract Task class.
 * It includes additional fields to manage recurrence, such as recurType and nextRefresh.
 */
public class RecurTask extends Task {

    private RecurType recurType;
    private LocalDate nextRefresh;

    /**
     * Constructor for creating a RecurTask with specified parameters.
     *
     * @param description The description of the task.
     * @param recurType The type of recurrence (DAILY, WEEKLY, MONTHLY).
     * @param nextRefresh The date for the next refresh.
     */
    public RecurTask(String description, RecurType recurType, LocalDate nextRefresh) {
        super(description);
        this.recurType = recurType;
        this.nextRefresh = nextRefresh;

        switch (recurType) {
        case DAILY:
            this.nextRefresh = LocalDate.now();
            break;
        case WEEKLY:
            this.nextRefresh = LocalDate.now().plusDays(6);
            break;
        case MONTHLY:
            this.nextRefresh = LocalDate.now().plusMonths(1).minusDays(1);
            break;
        default:
            System.out.println("Error creating recurTask");
        }
        check();
    }


    /**
     * Constructor for creating a RecurTask with specified parameters including status.
     *
     * @param status The status of the task (NOT_DONE, COMPLETE).
     * @param description The description of the task.
     * @param recurType The type of recurrence (DAILY, WEEKLY, MONTHLY).
     * @param nextRefresh The date for the next refresh.
     */
    public RecurTask(TaskStatus status, String description, RecurType recurType, LocalDate nextRefresh) {
        super(status, description);
        this.recurType = recurType;
        this.nextRefresh = nextRefresh;
        check();
    }

    /**
     * Checks and updates the task based on the recurrence type and next refresh date.
     */
    public void check() {
        LocalDate now = LocalDate.now();
        switch (this.recurType) {
        case DAILY:
            if (now.isAfter(this.nextRefresh)) {
                this.nextRefresh = now;
                super.setDone(MarkType.UNMARK);
            }
            break;
        case WEEKLY:
            while (now.isAfter(this.nextRefresh)) {
                this.nextRefresh = this.nextRefresh.plusWeeks(1);
                super.setDone(MarkType.UNMARK);
            }
            break;
        case MONTHLY:
            while (now.isAfter(this.nextRefresh)) {
                this.nextRefresh = this.nextRefresh.plusMonths(1);
                super.setDone(MarkType.UNMARK);
            }
            break;
        default:
            System.out.println("Invalid recurType");
            break;
        }
    }

    /**
     * Returns the string representation of the RecurTask.
     *
     * @return The formatted string with task details and recurrence information.
     */
    @Override
    public String toString() {
        String info;
        String label;
        int dayDiff = LocalDate.now().until(nextRefresh).getDays();
        switch (this.recurType) {
        case DAILY:
            label = "[RD]";
            info = "(Due today!)";
            if (super.getStatus() == TaskStatus.COMPLETE) {
                info = "(Complete! Resets tmr!)";
            }
            break;
        case WEEKLY:
            label = "[RW]";
            info = String.format("(Due in %s days!)", dayDiff);
            if (super.getStatus() == TaskStatus.NOT_DONE) {
                if (dayDiff == 0) {
                    info = "(Due today!)";
                }
            } else {
                info = String.format("(Complete! Resets in %s days)", dayDiff + 1);
                if (dayDiff == 1) {
                    info = "(Complete! Resets tmr!)";
                }
            }
            break;
        case MONTHLY:
            label = "[RM]";
            info = String.format("(Due in %s days!)", dayDiff);
            if (super.getStatus() == TaskStatus.NOT_DONE) {
                if (dayDiff == 0) {
                    info = "(Due today!)";
                }
            } else {
                info = String.format("(Complete! Resets in %s days)", dayDiff + 1);
                if (dayDiff == 1) {
                    info = "(Complete! Resets tmr!)";
                }
            }
            break;
        default:
            label = null;
            info = null;
            System.out.println("error in RecurTask::toString");
        }
        return String.format("%s%s %s %s", label, this.getStatusIcon(), super.toString(), info);
    }

    /**
     * Gets the string representation of the RecurType.
     *
     * @return The deadline date as a string.
     */
    public String getRecurType() {
        switch(recurType) {
        case DAILY:
            return "day";
        case WEEKLY:
            return "week";
        case MONTHLY:
            return "month";
        default:
            System.out.println("Error getting RecurType");
        }
        return null;
    }

    /**
     * Gets the string representation of the next refresh date.
     *
     * @return The string representation of the next refresh date.
     */
    public String getNextRefresh() {
        return nextRefresh.toString();
    }
}
