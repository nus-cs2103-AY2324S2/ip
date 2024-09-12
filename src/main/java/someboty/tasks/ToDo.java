package someboty.tasks;

/**
 * Handles a task of type "ToDo".
 * ToDo tasks only contain the description of the task.
*/
public class ToDo extends Task {
    public ToDo(String name) {
        super("");
        this.formatInput(name);
    }

    @Override
    protected void formatInput(String description) {
        this.name = description.trim();
    }

    @Override
    public String taskToCsv() {
        char status = this.isDone
                            ? '1'
                            : '0';
        
        return String.format("T,%c,%s\n",
                    status,
                    this.name);
    }

    /**
     * Creates a new ToDo task with the given details read from the csv file.
     * 
     * @param details Array of string describing the task.
     * @return An instance of ToDo with the given details.
     */
    protected static ToDo csvToTask(String[] details) {
        boolean isCompleted = details[1].equals("1");
        String description = details[2];

        ToDo task = new ToDo(description);
        task.setStatus(isCompleted);
        
        return task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
