public class Task {
    /* Task is a class, what does it do??? */
    private String name; 
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void doneTask() {
        this.isDone = true;
    }    

    public String getName() {
        return this.name;
    }

    public String checkStatus() {
        if (this.isDone) {
            return  this.name + " is complete!";
        } else {
            return this.name + " has yet to be completed.";
        }
    }

    public void completeTask() {
        this.isDone = true;
    }

    public void revertStatus() {
        this.isDone = false;
    }
    
}
