public class Deadline extends Task{

    private String deadline;

    public Deadline(String name, String deadline, boolean isDone){

        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String header(){

        int binary = super.isDone? 1: 0;
        return this.type() + binary;
    }

    @Override
    public String type(){

        return "D";
    }

    @Override
    public String additionalInfo(){

        return "/" + deadline;
    }
}
