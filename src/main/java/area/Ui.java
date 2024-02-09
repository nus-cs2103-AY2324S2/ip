package area;

public class Ui {

    public Ui() {
    };

    public void greetUser() {
        System.out.println("Hello! I'm Area.\n" +
                "What can I do for you?\n");
    }

    /**
     * prints a statement to acknowledge task has been added.
     * 
     * @param tasks
     */
    public void addTask(TaskList tasks) {
        System.out.println(
                "Got it. I've added this task:\n" + tasks.getTaskList().get(tasks.getNumberOfTasks() - 1).toString()
                        + "\n" + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list" + ".\n");
    }

    /**
     * prints a statement acknowledging a task has been marked done
     * 
     * @param num
     * @param tasks
     */
    public void taskDone(int num, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:\n " +
                tasks.getTaskList().get(num - 1).toString() + "\n");
    }

    /**
     * prints a statement to show task is undone again
     * 
     * @param num
     * @param tasks
     */
    public void taskUndone(int num, TaskList tasks) {
        System.out.println("OK, I've marked this task as not done yet:\n " +
                tasks.getTaskList().get(num - 1).toString() + "\n");
    }

    /**
     * prints a statement to show task has been deleted
     * 
     * @param deletedTask
     * @param tasks
     */
    public void deleteTask(Task deletedTask, TaskList tasks){
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString() + "\n" + "Now you have "
                + tasks.getNumberOfTasks() + " tasks in the list.\n");
    }

    /**
     * print out the list of task
     * 
     * @param tasks
     */
    public void showList(TaskList tasks){
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            System.out.println(i + 1 + "." + tasks.getTaskList().get(i).toString());
        }
        System.out.print("\n");
    }

    public void endChat(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     *
     * @param instruction
     */
    public void instructionError(String instruction){
        DukeException error = new DukeException(instruction);
        System.out.println(error.toString());
    }

    /**
     *
     * @throws Exception
     */
    public void showLoadingError() throws Exception{
        System.out.println("Loading Error: Your file does not exist");
    }

}
