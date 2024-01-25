class Events extends Task {
    private String start;
    private String end;

    Events(String description, String start, String end, int num) {
        super(description, num);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }
    @Override
    public String toString() {
        if (this.isMarked()) {
            return "[E][X] " + super.toString() + "(from: " + this.start.substring(5) + 
            " to: " + this.end.substring(3) + ")";
        } else {
            return "[E][ ] " + super.toString() + "(from: " + this.start.substring(5) + 
            " to: " + this.end.substring(3) + ")";
        }
    }
    @Override
    public String identifier() {
        return "[E]";
    }
}