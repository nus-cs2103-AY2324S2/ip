package someboty.tasks;

import someboty.exceptions.InputException;

/**
 * A abstract class to handle details of a task.
 * This class is extended by 3 sub-classes:
 * 1) ToDo
 * 2) Deadline
 * 3) Event
 */
abstract public class Task {
    protected String name;
    protected boolean isDone = false;

    /**
     * Constructor for Task.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Method body to be implemented by child classes.
     * Parses the description into relevant variables as some subtasks
     * contains more than just a name.
     * @param description Raw description of the task.
     */
    abstract protected void formatInput(String description);

    /**
     * compress task details into a line separated with ','
     * 
     * The format for all tasks should include:
     * - task type:           E, D, T         [Event, Deadline, ToDo respectively]
     * - completion status:   0, 1   
     * - task description:    String of words
     * 
     * specific task types should append other relevant details.
     * 
     * @return a single string line containing its description.
     */
    abstract public String taskToCsv();

    // Get description of the task.
    public String getName() {
        return this.name;
    }

    // Get task completion status.
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Set completion status of the task.
     * @param status true if completed, false otherwise.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return this.isCompleted()
            ? "[X] " + this.name
            : "[ ] " + this.name;
    }

    /**
     * Creates a new task with the given type and description.
     * @param type Type of the new task, represented as the first character of the actual type.
     * @param description Relevant details of the new task.
     * @return A new Task object containing the given descriptions.
     */
    public static Task createTask(char type, String description) {
        return type == 'T'
        ? new ToDo(description)
        : type == 'D'
        ? new Deadline(description)
        : type == 'E'
        ? new Event(description)
        : invalidTaskType();
    }

    private static Task invalidTaskType() {
        throw new InputException("Unknown task type. Check that your command is correct.");
    }

    /**
     * Creates a new task with the given details read from the csv file.
     * 
     * @param line String of a line in the csv file.
     * @return A task with the given details.
     */
    public static Task csvToTask(String line) {
        String[] details = line.split(",");
        assert details.length >= 3 : "Task::csvToTask  A csv line is broken.";

        switch(details[0]) {
            case "T":
                return ToDo.csvToTask(details);
            case "D":
                return Deadline.csvToTask(details);
            case "E":
                return Event.csvToTask(details);
        }

        throw new InputException("Error! An unknown task type is identified in csvToTask.");

    }
    
}
