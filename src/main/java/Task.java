import java.util.*;
import java.io.*;

public class Task {
    private String task;
    private boolean isDone;

    public Task(String s) {
        this.task = s;
        isDone = false;
    }

    public String getTask() {
        return task;
    }

    public boolean getStatus() {
        return isDone;
    }

    public Task setDone() {
        isDone = true;
        return this;
    }

    public Task setUndone() {
        isDone = false;
        return this;
    }

    public String toString() {
        return "[" +(isDone ? "X" : " ") + "] " + task;
    }
}
