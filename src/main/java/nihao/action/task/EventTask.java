package nihao.action.task;

import nihao.handler.DateTimeHandler;

import java.time.LocalDateTime;

public class EventTask extends Task{
    private LocalDateTime from;
    public String getFromString() {
        return DateTimeHandler.formatOutput(from);
    }
    private LocalDateTime to;
    public String getToString() {
        return DateTimeHandler.formatOutput(to);
    }
    public EventTask(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + getFromString() +
                " to: " + getToString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("invoked eventtask.equals");
        return obj instanceof EventTask && ((EventTask) obj).taskName.equals(taskName)
                && ((EventTask) obj).from.equals(from) && ((EventTask) obj).to.equals(to);
    }
}
