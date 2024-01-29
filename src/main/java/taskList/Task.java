package taskList;

import java.io.Serializable;

public class Task implements Serializable {
    protected String item;
    protected boolean isDone;

    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public void markItem(){
        this.isDone = true;
    }

    public void unmarkItem(){
        this.isDone = false;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + item;
    }  

}