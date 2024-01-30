public class Task {
    private boolean done = false;
    private String name;
    private String type; /* T, D or E*/
    private String date;

    public Task(String name){
        this.name = name;
    }
    public boolean isDone(){
        return this.done;
    }
    public String getName(){
        return this.name;
    }

    public void makeDone(){
        this.done = true;
    }
    public void makeUndone(){
        this.done = false;
    }
    /* add getType for each task type*/
    public String getType(){
        return "";
    }

    public void setDone(Boolean state) {
        this.done = state;
    }

    public String getTime(){
        return "";
    }
    public String getStatus(){
        if (this.isDone()) {
            return this.getType() + "[X] " + this.getName() + this.getTime();
        } else {
            return this.getType() + "[ ] " + this.getName() + this.getTime();
        }
    }
}
