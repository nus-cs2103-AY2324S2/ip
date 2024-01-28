public class Deadline extends Task{

    private String deadline;

    public Deadline(String content,String deadline) {
        super(content);
        this.deadline = deadline;
    }

    public String getType(){
        return "D";
    }

    public String getDeadline(){
        return this.deadline;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
