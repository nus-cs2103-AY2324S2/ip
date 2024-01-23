public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description);
        super.taskType = 'D';
        this.by = by;
    }
    @Override
    public String toString(){
        String str = String.format(
                super.toString() + " (%s)", this.by);
        return str;
    }
}
