/**
 * Class for adding a task into task list
 */
public class Add implements Command{

    private String text;

    /**
     * Constructor
     * add task to task list.
     * @param text the description of a task
     */
    public Add(String text) {
        this.text=text;
        Task.task_list.add(new Task(text));
    }

    /**
     * Override the interface method.
     */
    @Override
    public void reply() {
        System.out.printf("    added: %s\n",this.text);
    }
}
