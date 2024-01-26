/**
 * Class for listing all the tasks
 */
public class List implements Command{
    private TaskList taskList;
    public List(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * Override the reply method in interface
     * iterate through the task list and print out everything.
     */
    @Override
    public void reply() {
        System.out.println("    Here are the tasks in your list:");
        int i=0;
        for (Task task : taskList.getTaskList()) {
            System.out.printf("    %s.%s\n",++i,task);
        }
    }
}
