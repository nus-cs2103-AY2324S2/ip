public class Task {
    private String name;
    private Boolean status = false;

    public Task(String name, Boolean status) {
        this.name = name;
        this.status = false;
    }

    public Boolean isDone() {
        return this.status;
    }

    public void finishTask() {
        this.status = true;
    }

    public void redoTask() {
        this.status = false;
    }


    @Override public String toString() {
        if (this.status) {
            return "[X] " + this.name;
        }
        return "[ ] " + this.name;
    }




}
