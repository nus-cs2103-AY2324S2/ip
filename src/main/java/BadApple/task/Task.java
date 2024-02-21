package BadApple.task;

public class Task {

    protected String description;
    protected boolean isComplete = false;

    public Task(String desc) {
        this.description = desc;
    }

    public String mark(boolean b, int taskIndex) {
        String reply = "Nothing got marked";
        String MARK = b ? "marked" : "unmarked"; //use enums if more needed
        if (TaskList.tasks.isEmpty()) {
            return "There's nothing to mark, Yay!";
        }
        try {
            if (taskIndex > TaskList.tasks.size() || taskIndex < 0) {
                return "You don't have that task silly!";
            }
            this.isComplete = b;
            if (!Tracker.isSuppressingMsgs) {
                return "I've " + MARK + " task " + (taskIndex+1) + "\n" + this;
            }
        } catch(NumberFormatException | IndexOutOfBoundsException  e) {
            System.out.println("Usage: mark <taskNumber>");
        }
        return reply;
    }
    @Override
    public String toString() {
        String completion = isComplete ? "[X]" : "[]";
        return completion + " " + description;
    }

    public String brief() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof Task) {
            Task task = (Task) o;
            return task.description.equals(this.description);
        }

        return false;
    }
}
