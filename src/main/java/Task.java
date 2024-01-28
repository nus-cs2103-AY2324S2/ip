enum Task {
    TODO("T") {
        @Override
        public String getMessage() {
            return "[" + getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask();
        }
    },
    DEADLINE("D") {
        @Override
        public String getMessage() {
            return "[" + getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (by: " + getTo() + ")";
        }
    },
    Event("E") {
        @Override
        public String getMessage() {
            return "[" + getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (from: " + getFrom() + " to:" + getTo() + ")";
        }
    };

    private String task;
    private boolean isDone;
    private String icon;
    private String to;
    private String from;
    Task(String icon) {
        this.icon = icon;
        this.isDone = false;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String getMessage();

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }
}
