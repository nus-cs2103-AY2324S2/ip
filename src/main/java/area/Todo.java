package area;

public class Todo extends Task {

    /**
     * Create an object of type Todo
     * 
     * @param description
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Displays the object in a readable manner
     * 
     * @return String
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
