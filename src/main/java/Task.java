class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }    

    public String taskDone() {
        isDone = true;
        return "    Nice! I've marked this task as done: \n        [X] " + name;
    }

    public String taskUndone() {
        isDone = false;
        return "   OK, I've marked this task as not done yet: \n        [ ] " + name;
    }    


}
