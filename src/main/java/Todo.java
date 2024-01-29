import java.io.Serializable;
<<<<<<< HEAD
=======

public class Todo extends Task implements Serializable {
>>>>>>> branch-Level-8

public class Todo extends Task implements Serializable {

    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}