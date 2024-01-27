public class Task {
    private String name;
    private Boolean isDone;
    public Task(String name){

        this.name = name;
        this.isDone = false;
    }

    public void mark(){
        isDone = true;
    }

    public void unmark(){
        isDone = false;
    }

    @Override
    public String toString(){

        String checkbox = this.isDone? "[x]" : "[ ]";

        return checkbox + " " + name;
    }
}
