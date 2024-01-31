package Tasks;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) {
        super("");
        this.format(description);
    }

    protected void format(String description) {
        String errorMessage = 
            "Unable to identify the start and/or end date. Make sure to follow the format:\n"
            + "event DESCRIPTION /from START /to END";
    

        try {
            String[] listOfStrings = description.split("/");
            this.name = listOfStrings[0].trim();
            this.from = listOfStrings[1];
            this.to = listOfStrings[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }

        if (!this.from.startsWith("from ") || !this.to.startsWith("to ")) {
            throw new IllegalArgumentException(errorMessage);
        }

        this.from = this.from.substring(5).trim();
        this.to = this.to.substring(3).trim();
    }
    
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.from, this.to);
    }
}
