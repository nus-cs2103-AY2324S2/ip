package tasks;

import exceptions.KewgyException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String time1;
    protected String time2;

    public enum TaskType {
        T,
        D,
        E;

        public static TaskType getType(String s) {
            switch (s) {
                case "T":
                    return T;
                case "D":
                    return D;
                case "E":
                    return E;
                default:
                    return null;
            }
        }
    }

    public Task(String description) throws KewgyException {
        if (description.isEmpty()) {
            throw new KewgyException("Description cannot be empty!");
        };

        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateTime(String... times) throws KewgyException { }
}