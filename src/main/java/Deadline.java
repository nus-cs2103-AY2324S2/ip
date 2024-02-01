public class Deadline extends Task{
    private String deadline;
    public Deadline(String s, String deadline){
        super(s);
        this.deadline = deadline;
    }

    public Deadline(String s, boolean mark, String deadline){
        super(s);
        this.deadline = deadline;
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }
    @Override
    public String toString(){
        String X = this.getMark() ? "X" : " ";
        return "[D]"+"[" + X + "] " + this.getItem() + " (by: " + deadline + ")";
    }
}
