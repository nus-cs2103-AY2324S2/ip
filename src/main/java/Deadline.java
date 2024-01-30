public class Deadline extends Task{
    private String date;

    public Deadline(String description, String date){
        super.description = description;
        this.date = date;
    }

    public Deadline(String description, String date, boolean isDone){
        super.description = description;
        super.isDone = isDone;
        this.date = date;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toWrite(){
        return "D | " + super.toWrite() + " | " + this.date;
    }

    @Override
    public String toString(){
        return this.description + " (by: "+ this.date + ")";
    }
}
