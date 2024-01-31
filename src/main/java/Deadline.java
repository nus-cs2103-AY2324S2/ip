public class Deadline extends Task{
    String deadline;
    public Deadline(String name,String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D] " + super.toString() + "("+this.deadline+")";
    }
}
