package Tasks;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String description) {
        super("");
        this.format(description);
    }

    protected void format(String description) {
        String errorMessage = 
            "Unable to identify the start and/or end date. Make sure to follow the format:\n"
            + "'event DESCRIPTION /from START /to END'";

        try {
            this.deadline = description.split("/by ")[1].trim();

        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }

        if (this.deadline.equals("")) {
            throw new IllegalArgumentException(errorMessage);
        }

        this.name = description.split("/by")[0].trim();
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
