import java.util.ArrayList;
import java.util.Arrays;

public class Deadline extends Task{
    protected String by;
    private Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * This is a Factory Method that generates an instance
     * @param s an ArrayList after tokenizing the query.
     */
    public static Deadline extractDetails(ArrayList<String> s) throws BadAppleException{
        StringBuilder taskName = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        int separatorIndex = s.indexOf("/by");
        if (separatorIndex > 0) {
            for (int i = 1; i < separatorIndex; i++) {
                taskName.append(s.get(i)).append(" ");
            }
            for (int i = separatorIndex + 1; i < s.size();i++) {
                deadline.append(s.get(i)).append(" ");
            }
            return new Deadline(taskName.toString(), deadline.toString());
        } else {
            throw new BadAppleException("Usage: deadline TaskName /by DueTime");
        }

    }

    // in case anyone tries to throw an un-formatted string, the program still runs
    public static Deadline extractDetails(String s) {
        return extractDetails(new ArrayList<>(Arrays.asList(s.split(" "))));
    }
}
