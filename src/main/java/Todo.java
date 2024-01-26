import java.util.ArrayList;
import java.util.Arrays;

public class Todo extends Task{

    public static Todo extractDetails(String s) {
        // This removes the "todo" from the front
        return new Todo(s.substring(5));
    }

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
