package goblin.task;

public class ToDos extends Task {

    /**
     * create a new ToDos object
     * @param description what the task is
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * print the task
     */
    @Override
    public void print() {
        System.out.println( "\t" + "[T]" + getStatusIcon() + getDescription());
    }

    /**
     * a getter for the type
     * @return the type
     */
    @Override
    public String type() {
        return ("[T]");
    }

    /**
     * toString
     * @return a string
     */
    @Override
    public String toString() {
        return isDone + " todo " + getDescription();
    }

    /**
     * a string
     * @return a string
     */
    @Override
    public String notPrint() {
        return "\t" + "[T]" + getStatusIcon() + getDescription();
    }
}
