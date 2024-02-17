import java.io.Serializable;

public class Task implements Serializable {

    private String name;
    private boolean done;

    @Override
    public String toString() {

        String taskString = "[ ] ";

        String temp = "[ ] ";

        if(this.done){
            temp = "[X] ";
        }

        return taskString + temp + this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public Task(String name) {
        this.name = name;
        this.done = false;
    }
}
