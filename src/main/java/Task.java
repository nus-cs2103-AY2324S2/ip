public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //function to mark as done
    public void markAsDone() {
        this.isDone = true;
    }
    //function to unmark
    public void unMark(){
        this.isDone = false;
    }
    public String toString(){
        return "["+ this.getStatusIcon() +"]" + this.description;
    }

    //...
}

