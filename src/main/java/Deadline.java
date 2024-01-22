public class Deadline extends Task{

    public String by;
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    @Override
    public String toString(){
        String tag = super.getTag() ? "[X]" : "[ ]";
        return "[D]" + tag + " " + super.getName() + "(by: " + this.by + ")";
    }
}
