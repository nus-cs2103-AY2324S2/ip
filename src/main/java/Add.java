/**
 * Class for adding a task into task list
 */
public class Add implements Command{
    private Task task;

    /**
     * Constructor
     * add task to task list.
     * Identify which type of task is it.
     * @param text the description of a task
     */
    public Add(String text) {
        //String[] token = text.split("/");
        //String descrip = token[0];
        if (text.startsWith("todo ")) {
            this.task=new Todo(text.substring(5));
            Task.task_list.add(this.task);

        } else if (text.startsWith("deadline ")) {
            String[] token = text.split("/");
            //if token.length != 2 throw exception
            String descrip = token[0].substring(9);
            String by = token[1];
            //if by not start with by, throw exception
            this.task=new Deadline(descrip, by.substring(3));
            Task.task_list.add(this.task);
        } else if (text.startsWith("event ")) {
            String[] token = text.split("/");
            //if token.length != 3 throw exception
            String descrip = token[0].substring(6);
            String from = token[1];
            String to = token[2];
            //if (from.startsWith("from ") && to.startsWith("to ")) {
            this.task=new Event(descrip, from.substring(5), to.substring(3));
            Task.task_list.add(this.task);
        } //else left for exception
    }

    /**
     * Override the interface method.
     */
    @Override
    public void reply() {
        System.out.printf(
                "    Got it. I've added this task:\n      %s\n    Now you have %s tasks in the list.\n"
                ,this.task,Task.task_list.size());
    }
}
