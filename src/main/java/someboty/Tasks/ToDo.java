package someboty.Tasks;

/**
 * Handles a task of type "ToDo".
 * ToDo tasks only contain the description of the task.
*/
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    protected void formatInput(String description) {
        return; // ToDo tasks do not need to format anything.
    }

    @Override
    public String toCSV() {
        char status = this.isDone
                            ? '1'
                            : '0';
        
        return String.format("T,%c,%s\n",
                    status,
                    this.name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
