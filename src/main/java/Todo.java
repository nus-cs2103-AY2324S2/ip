public class Todo extends Task{
    public Todo(String description){
        super.description = description;
    }


    public String getTaskIcon(){
        return "T";
    }
}
