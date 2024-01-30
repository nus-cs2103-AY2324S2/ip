public class ToDo extends Task {
    
    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String saveFormat() {
        return String.format("%s;;%s",
                "T", super.saveFormat());
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }

}