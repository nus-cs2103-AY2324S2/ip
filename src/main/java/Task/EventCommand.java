package task;

public class EventCommand extends Command {
    private String taskDescription;
    private String startTime;
    private String endTime;

    public EventCommand(String body) {
        super(body);
        String[] parts = body.split("/from", 2);
        String[] parts2 = parts[1].split("/to", 2);
        this.taskDescription = parts[0].trim();
        this.startTime = parts2[0].trim();
        this.endTime = parts2[1].trim();
    }

    public boolean execute(TaskList list) throws DukeException {
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of an event cannot be empty.",
                    "The description of an event cannot be empty.");
        } else if (startTime.isEmpty()) {
            throw new EmptyDateTimeException("The start time of an event cannot be empty.",
                    "The start time of an event cannot be empty.");
        } else if (endTime.isEmpty()) {
            throw new EmptyDateTimeException("The end time of an event cannot be empty.",
                    "The end time of an event cannot be empty.");
        }
        Task task = new Event(taskDescription, startTime, endTime);
        list.addTask(task);
        System.out.println("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        return true;
    }
}
