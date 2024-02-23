package task;

import javax.xml.namespace.QName;


public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon(){
        return(isDone? "X" : " ");
    }

    public Boolean getStatus() { return isDone; }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }

    public String getStatusNum() {
        if(this.isDone) {
            return "1";
        }else {
            return "0";
        }
    }

//    public String getTask() {
//        return this.name;
//    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name ;
    }

    public String toFileString() {
        return " " + " | " + getStatusNum() + " | " + this.name ;
    }


}