public class Task {
    private String taskName;
    private boolean taskDone = false;
    private String typeOfTask;
    private String[] timeFromAndTo = new String[] {"NA", "NA"};

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName, String typeOfTask) {
        this.taskName = taskName;
        this.typeOfTask = typeOfTask;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskType() { return this.typeOfTask; }

    public boolean isTaskDone() {
        return this.taskDone;
    }

    public String getStatusIcon() {
        return (this.taskDone ? "X" : " "); // mark done task with X
    }

    public void setTime(String[] times) {
        this.timeFromAndTo[0] = times[0];
        this.timeFromAndTo[1] = times[1];
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

    public void changeStatus(String action) {
        if (action.equals("mark")) {
            this.taskDone = true;
        } else {
            this.taskDone = false;
        }
        MessagePrinter.markActionPrint(action, this);
    }

    public void changeStatus(int action) {
        if (action == 1) {
            this.taskDone = true;
        } else {
            this.taskDone = false;
        }
    }

    public String[] getTimes() {
        return this.timeFromAndTo;
    }

}
