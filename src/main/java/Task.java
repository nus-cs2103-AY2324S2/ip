public class Task {

    public String name;
    public boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.checkbox() + " " + this.name;
    }

    public String typeOfTask() {
        return " ";
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public String checkbox() {
        return String.format("[%s][%s]", this.typeOfTask(), this.getStatusIcon());
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}