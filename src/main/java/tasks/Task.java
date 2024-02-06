package tasks;

import parser.Parser;
import exceptions.ArgumentException;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static Task makeTask(String[] taskData) throws ArgumentException {
        String taskType = taskData[0];
        switch (taskType) {
        case "T":
            return new ToDo(taskData[2], taskData[1]);
        case "D":
            String[] args = Parser.parseDeadlineArgument(taskData[2]);
            return new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()), taskData[1]);
        case "E":
            args = Parser.parseEventArgument(taskData[2]);
            return new Event(args[0].trim(), LocalDate.parse(args[1].trim()),
                    LocalDate.parse(args[2].trim()), taskData[1]);
        default:
            throw new ArgumentException("Did not recognize task type " + taskType);
        }
    }

    public static Task makeTask(String taskType, String taskArgument) throws ArgumentException {
        switch (taskType) {
        case "todo":
            if (taskArgument.length() == 0) {
                throw new ArgumentException("Insufficient argument provided for todo task");
            }
            return new ToDo(taskArgument);
        case "deadline":
            String[] args = Parser.parseDeadlineArgument(taskArgument);
            return new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()));
        case "event":
            args = Parser.parseEventArgument(taskArgument);
            return new Event(args[0].trim(), LocalDate.parse(args[1].trim()),
                    LocalDate.parse(args[2].trim()));
        default:
            throw new ArgumentException("Did not recognize task type " + taskType);
        }
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String status) {
        this.description = description;
        this.isDone = false;
        if (status.equals("Y")) {
            this.isDone = true;
        }
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String toSaveFormat() {
        if (this.isDone) {
            return "Y " + this.description;
        } else {
            return "N " + this.description;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
