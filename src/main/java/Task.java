abstract public class Task {

    protected String fullTaskDescription; //include timeline
    protected String description;
    protected boolean isDone;

//    public Task(String description) {
//        this.description = description;
//        this.isDone = false;
//    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }



    public void markComplete() {
        this.isDone = true;
    }

    public void markIncomplete() {
        this.isDone = false;
    }

    @Override
    public String toString(){
        String str;
        str = "[" + this.getStatusIcon() + "] ";
        return str;
    }

    public abstract String toSave();

}
