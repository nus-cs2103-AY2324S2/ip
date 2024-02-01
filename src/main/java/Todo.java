import java.util.ArrayList;
import java.util.Arrays;

public class Todo extends Task {

    private Todo(String desc) {
        super(desc);
    }

    /**
     * This is a Factory Method that generates a Todo instance
     * @param s an ArrayList after tokenizing the query.
     */
    public static Todo extractDetails(String s) throws BadAppleException {
        if (s.split(" ").length == 1) {
            throw new BadAppleException("There is nothing here.");
        }
        // This removes the "todo" from the front
        return new Todo(s.substring(5));
    }

    @Override
    public String toString() {
        return "Todo" + " " + super.toString();
    }
}
