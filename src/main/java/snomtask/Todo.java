package snomtask;

/**
 * Todo implements a todo task
 * that the user needs to do.
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }



}
