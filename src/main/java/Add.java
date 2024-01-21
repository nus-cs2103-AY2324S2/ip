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
     * @throws DukeException wrong inputs might happens
     */
    public Add(String text) throws DukeException{
        if (text.startsWith("todo")) {
            if (text.length()<=5) {
                throw new EmptyTextException("description","todo");
            }
            String descrip=text.substring(5);
            this.task=new Todo(descrip);
            Task.task_list.add(this.task);
        } else if (text.startsWith("deadline")) {
            String[] token = text.split("/");
            if (token.length != 2) {
                throw new WrongUsageException("deadline xxx /by xxx");
            }
            String by = token[1];
            if (token[0].length()<=9) {
                throw new EmptyTextException("description","deadline");
            }
            String descrip = token[0].substring(9);
            if (!by.startsWith("by ")) {
                throw new WrongUsageException("deadline xxx /by xxx");
            }
            if (by.length()<=3) {
                throw new EmptyTextException("due","deadline");
            }
            this.task=new Deadline(descrip, by.substring(3));
            Task.task_list.add(this.task);
        } else if (text.startsWith("event")) {
            String[] token = text.split("/");
            if (token.length != 3) {
                throw new WrongUsageException("event xxx /from xxx /to xxx");
            }
            String from = token[1];
            String to = token[2];
            if (token[0].length()<=6) {
                throw new EmptyTextException("description","event");
            }
            String descrip = token[0].substring(6);
            if (!from.startsWith("from ")) {
                throw new WrongUsageException("event xxx /from xxx /to xxx");
            }
            if (!to.startsWith("to ")) {
                throw new WrongUsageException("event xxx /from xxx /to xxx");
            }
            if (from.length()<=5) {
                throw new EmptyTextException("start","event");
            }
            if (to.length()<=3) {
                throw new EmptyTextException("end","event");
            }
            this.task=new Event(descrip, from.substring(5), to.substring(3));
            Task.task_list.add(this.task);
        } else {
            throw new CommandNotDefinedException();
        }
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
