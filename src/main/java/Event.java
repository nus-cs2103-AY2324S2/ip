public class Event extends Task{
    protected String eventFrom;
    protected String eventTo;
    public Event(String taskDescription) {
        super(taskDescription, TaskType.EVENT);
        parseEvent(taskDescription);
    }

    private String parseEvent(String taskDescription) {
        String[] parts = taskDescription.split("/from", 2);

        if (parts.length == 2) {
            String[] timings = parts[1].split("/to", 2);
            if (timings.length == 2) {
                eventFrom = timings[0].trim();
                eventTo = timings[1].trim();
                return parts[0].trim() + " (from: " + eventFrom + " to: " + eventTo + ")";
            }
        }

        throw new IllegalArgumentException("Invalid task description for event");
    }

    public String getStartTime() {
        return eventFrom;
    }

    public String getEndTime() {
        return eventTo;
    }

    @Override
    public void setTaskDescription(String taskDescription) {
        super.setTaskDescription(parseEvent(taskDescription));
        parseEvent(taskDescription);
    }

}
