package Tasks;

/**
* Handles a task of type "Event".
* Event tasks contains the following details:
* 1) Description of the task.
* 2) Event start date/time.
* 3) Event end date/time.
*/
public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) {
        super("");
        this.formatInput(description);
    }

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    // Get start date.
    public String getStart() {
        return this.from;
    }

    // Get end date.
    public String getEnd() {
        return this.to;
    }

    @Override
    protected void formatInput(String description) {
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

    @Override
    public String toCSV() {
        char status = this.isDone
                            ? '1'
                            : '0';

        return String.format("E,%c,%s,%s,%s\n",
                    status,
                    this.name,
                    this.from,
                    this.to
                    );
    }
    
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.from, this.to);
    }
}
