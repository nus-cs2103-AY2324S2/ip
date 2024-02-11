package Panna;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {
    protected String taskName;
    protected boolean isDone;
    private LocalDate start;
    private LocalDate end;

    private LocalDate deadline;

    protected char taskType;


    // we need to use different constructors for the different classes
    // for todo: only taskName
    // for deadline: taskName + deadline
    // for event: taskName + start + end

    public Task() {


    }

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
        taskType = 'T';
    }

    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
        isDone = false;
        taskType = 'D';
    }

    public Task(String taskName, LocalDate start, LocalDate end) {
        this.taskName = taskName;
        this.start = start;
        this.end = end;
        isDone = false;
        taskType = 'E';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public LocalDate getStart() {
        return tstart;
    }

    public LocalDate getEnd() {
        return end;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + taskName;
    }
}




