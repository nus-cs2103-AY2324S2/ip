import javax.xml.namespace.QName;


public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return(isDone? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }

    public String getTask() {
        return this.name;
    }

}
