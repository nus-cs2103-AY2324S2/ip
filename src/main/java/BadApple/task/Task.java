package BadApple.task;

import static java.lang.Integer.parseInt;

public class Task {

    protected String description;
    protected boolean isComplete = false;

    public Task(String desc) {
        this.description = desc;
    }

    public String mark(boolean b, int taskIndex) {
        String reply = "";
        String MARK = b ? "marked" : "unmarked"; //use enums if more needed
        if (TaskList.tasks.isEmpty()) {
            reply = "There's nothing to mark, Yay!";
            System.out.println(reply);
        }
        try {
            if (taskIndex > TaskList.tasks.size() || taskIndex < 0) {
                reply = "You don't have that task silly!";
                System.out.println(reply);
            }
            this.isComplete = b;
            if (!Tracker.isSuppressingMsgs) {
                reply = "I've " + MARK + " task " + (taskIndex+1) + "\n" + this;
                System.out.println(reply);
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
