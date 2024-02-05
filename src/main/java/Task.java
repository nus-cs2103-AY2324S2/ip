public class Task {
    /* Task is a class, what does it do??? */
    private String name; 
    private boolean isDone = false;
    private char taskType;

    public Task(String name, String task) {
        this.name = name;
        this.taskType = task.charAt(0);
    }

    public void doneTask() {
        this.isDone = true;
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

    public void completeTask() {
        this.isDone = true;
    }

    public void revertStatus() {
        this.isDone = false;
    }
    
}
