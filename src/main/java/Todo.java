public class Todo extends Task{

    public Todo(String description){
        super.description = description;
    }

    public Todo(String description, boolean isDone){
        super.description = description;
        super.isDone = isDone;
    }

    public String getTaskIcon(){
        return "T";
    }

    @Override
    public String toWrite(){
        return "T | " + super.toWrite();
    }
}
