package task;

import duke.ProgramState;

public class EventCommand extends Command {
    private String taskDescription;
    private String startTime;
    private String endTime;

    public EventCommand(String body) {
        super(body);
        String[] parts = body.split("/from|/to", 3);
        this.taskDescription = parts[0].trim();
        this.startTime = parts.length > 1 ? parts[1].trim() : "";
        this.endTime = parts.length > 2 ? parts[2].trim() : "";
    }

    public String execute(TaskList list, ProgramState state) throws DukeException {
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
        String response = ("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
