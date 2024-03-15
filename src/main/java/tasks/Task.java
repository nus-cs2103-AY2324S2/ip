package tasks;

import cro.CroException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;

    protected List<String> tags = new ArrayList<>();

    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getSaveLine() { return "Unknown Task Type";}

    public String getDescription() { return this.description;}

    public String addTag(List<String> splitStr) throws CroException {
        String tag = String.join(" ", splitStr);
        if (description.equals("")) {
            throw new CroException("tag cannot be empty!");
        }
        this.tags.add(tag);
        return String.format("The tag: [%s] has been added", tag);
    }
}
