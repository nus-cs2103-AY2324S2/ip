class Task {
    protected String name;
    protected boolean isDone;
    protected String type = " ";

    //add
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }    

    public String getName() {
        return this.name;
    }

    public String getTypeIcon() {
        return this.type;
    }       

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }   
    
    public void taskDone() {
        this.isDone = true;
    }

    public void taskUndone() {
        this.isDone = false;
    }    

    public String toSave() {
        return  " | " + (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getName();
    }

}
