public class Task {

    private String name;
    private boolean done;

    @Override
    public String toString() {

        String temp = "[ ] ";

        if(done){
            temp = "[X] ";
        }

        return temp + name;
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
