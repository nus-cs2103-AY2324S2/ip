public class Todo extends Task{

    public Todo(String description){
        super.description = description;
    }

    public String getTaskIcon(){
        return "T";
    }

    @Override
    public String toWrite(){
        return "T | " + super.toWrite();
    }
}
