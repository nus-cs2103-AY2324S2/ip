public class Deadline extends Task{
    private String deadline;
    public Deadline(String s, String deadline){
        super(s);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        String X = this.getMark() ? "X" : " ";
        return "[D]"+"[" + X + "] " + this.getItem() + " (by: " + deadline + ")";
    }
}
