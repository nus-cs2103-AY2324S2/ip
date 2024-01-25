class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String description, boolean hasDone, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public static Event buildEvent(String input) {
        String[] formattedInput = input.split("/");
        String taskDescription = formattedInput[0].substring(5).trim();
        String startDate = formattedInput[1].substring(4).trim();
        String endDate = formattedInput[2].substring(2).trim();
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
