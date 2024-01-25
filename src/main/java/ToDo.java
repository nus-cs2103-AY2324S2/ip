public class ToDo extends Task {
    
    ToDo(String description, int num) {
        super(description, num);
        
    }

    @Override
    public String toString() {
        if (this.isMarked()) {
            return "[T][X] " + super.toString();
        } else {
            return "[T][ ] " + super.toString();
        }
    }

    @Override
    public String identifier() {
        return "[T]";
    }
    
}
