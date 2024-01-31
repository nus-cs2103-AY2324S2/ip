package tiny.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    //add
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }      

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); 
    }   
    
    public void taskDone() {
        this.isDone = true;
    }

    public void taskUndone() {
        this.isDone = false;
    }

    public boolean descriptionSearch(String search) {
        return description.contains(search);
    }    

    public String toSave() {
        return  " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
