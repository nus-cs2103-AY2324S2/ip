package duke.tasks;

public abstract class Task {
    /* Task is a class, what does it do??? */
    public String name; 
    public boolean isDone;
    public char taskType;
    // static String COMPLETED_MESSAGE_END = " is complete!";
    // static String INCOMPLETE_MESSAGE_END = " has yet to be completed.";

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, String isDone) {
        this.name = name;

        if (isDone.equals("false")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }
    
    public Boolean checkDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public String checkStatus() {
        if (this.isDone) {
            return "[" + this.taskType + "] " + this.name + " is complete!";
        } else {
            return "[" + this.taskType + "] " + this.name + " has yet to be completed.";
        }
    }

    public abstract String getAttributes();

    public void completeTask() {
        this.isDone = true;
    }

    public void revertStatus() {
        this.isDone = false;
    }
    
}
