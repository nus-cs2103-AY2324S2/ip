public class Deadline extends Task{
    String deadline;
    public Deadline(String name,boolean mark,String deadline) {
        super(name,mark);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D] " + super.toString() + "("+this.deadline+")";
    }

    @Override
    public String toStringFile(){
        return "D|" + super.toStringFile() + "|"+this.deadline;
    }
}
