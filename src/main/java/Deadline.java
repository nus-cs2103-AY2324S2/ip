public class Deadline extends Task{

    private String by;
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    @Override
    public String toString(){
        String tag = super.getTag() ? "[X]" : "[ ]";
        return "[D]" + tag + " " + super.getName().strip() + " (by: " + this.by + ")";
    }
}
