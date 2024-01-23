public class Deadline extends Task{
    private String date;

    public Deadline(String description, String date){
        super.description = description;
        this.date = date;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString(){
        return this.description + " (by: "+ this.date + ")";
    }
}
