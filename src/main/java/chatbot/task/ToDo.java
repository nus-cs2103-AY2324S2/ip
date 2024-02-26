package chatbot.task;

/**
 * Encapsulates the data and behaviour of a chatbot.task.ToDo task.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class ToDo extends Task {
    /**
     * Constructs a new chatbot.task.ToDo task with the given name.
     *
     * @param name The name of the chatbot.task.ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    @Override
    public String saveTask() {
        return "T | " + super.saveTask();
    }
}
