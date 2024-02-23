/* import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
*/

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        /*
		try{
			this.deadline = LocalDate.parse(deadline);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format. Please use yyyy--mm-dd.");
			this.deadline = null;
		}*/
        this.deadline = deadline;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return super.toString() + " (by " + deadline + ")";
    }
}
