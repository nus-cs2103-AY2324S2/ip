public class Todo extends Task{
    public Todo(String content) {
        super(content);
    }

    public String getType(){
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
