package ukecat;

import java.time.LocalDate;



public class RecurTask extends Task {

    private RecurType recurType;
    private LocalDate nextRefresh;

    public RecurTask(String description, RecurType recurType, LocalDate nextRefresh) {
        super(description);
        this.recurType = recurType;
        this.nextRefresh = nextRefresh;

        switch (recurType) {
        case DAILY:
            this.nextRefresh= LocalDate.now();
            break;
        case WEEKLY:
            this.nextRefresh= LocalDate.now().plusDays(6);
            break;
        case MONTHLY:
            this.nextRefresh= LocalDate.now().plusMonths(1).minusDays(1);
            break;
        default:
            System.out.println("Error creating recurTask");
        }
        check();
    }


    public RecurTask(TaskStatus status, String description, RecurType recurType, LocalDate nextRefresh) {
        super(status, description);
        this.recurType = recurType;
        this.nextRefresh = nextRefresh;
        check();
    }

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

    @Override
    public String toString() {
        String info;
        String label;
        int dayDiff = LocalDate.now().until(nextRefresh).getDays();
        switch (this.recurType) {
        case DAILY:
            label = "[RD]";
            info = "(Due today!) ";
            if (super.getStatus() == TaskStatus.COMPLETE) {
                info = "(Complete! Resets tmr!)";
            }
            break;
        case WEEKLY:
            label = "[RW]";
            info = String.format("(Due in %s days!) ", dayDiff);
            if (super.getStatus() == TaskStatus.NOT_DONE) {
                if (dayDiff == 0) {
                    info = "(Due today!) ";
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
            info = String.format("(Due in %s days!) ", dayDiff);
            if (super.getStatus() == TaskStatus.NOT_DONE) {
                if (dayDiff == 0) {
                    info = "(Due today!) ";
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

    public String getNextRefresh() {
        return nextRefresh.toString();
    }
}
