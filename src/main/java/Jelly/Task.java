package Jelly;
public class Task {
    private String name;
    protected Boolean isDone;
    public Task(String name, boolean isDone){

        this.name = name;
        this.isDone = isDone;
    }

    public String header(){

        return "NULL";
    }

    public String type(){

        return "N";
    }

    public String additionalInfo(){

        return "";
    }

    public void mark(){
        isDone = true;
    }

    public void unmark(){
        isDone = false;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){

        String checkbox = this.isDone? "[x]" : "[ ]";

        return checkbox + " " + name;
    }
}
