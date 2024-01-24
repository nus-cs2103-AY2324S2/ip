public class Task {
    protected String name;
    protected boolean completed;

    //constructor
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markTask() {
        this.completed = true;
        System.out.println("Upz la, mark for you already!");
        System.out.println(this.toString());
    }

    public void unmarkTask() {
        this.completed = false;
        System.out.println("Eh wake up your idea, faster finish can or not?? Unmark for you already la!");
        System.out.println(this.toString());
    }

    public String getStatusIcon() {
        return (completed ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
