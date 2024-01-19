class Event extends Task {
    private final String startDate;
    private final String endDate;

    public static Event buildEvent(String input) {
        String[] splitedInput = input.split("/");
        String taskDescription = splitedInput[0].substring(5).trim();
        String startDate = splitedInput[1].substring(4).trim();
        String endDate = splitedInput[2].substring(2).trim();
        return new Event(taskDescription, startDate, endDate);
    }


    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
