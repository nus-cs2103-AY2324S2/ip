package area;

import java.util.ArrayList;

/**
 * Returns responses to user instructions in the form of printed statements. It
 * starts with greeting the user and ends with the command bye. If any task is
 * added or modified,
 * Ui will return a statement accordingly to inform user that user's instructions
 * have been followed.
 */
public class Ui {

    public Ui() {
    };

    public String greetUser() {
        return "Hello! I'm Area.\n" +
                "What can I do for you?\n";
    }

    /**
     * return a statement to acknowledge task has been added.
     * 
     * @param tasks
     */
    public String addTask(TaskList tasks) {
        return "Got it. I've added this task:\n" + tasks.getTaskList().get(tasks.getNumberOfTasks() - 1).toString()
                + "\n" + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list" + ".\n";
    }

    /**
     * return a statement acknowledging a task has been marked done
     * 
     * @param num
     * @param tasks
     */
    public String taskDone(int num, TaskList tasks) {
        return "Nice! I've marked this task as done:\n " +
                tasks.getTaskList().get(num - 1).toString() + "\n";
    }

    /**
     * return a statement to show task is undone again
     * 
     * @param num
     * @param tasks
     */
    public String taskUndone(int num, TaskList tasks) {
        return"OK, I've marked this task as not done yet:\n " +
                tasks.getTaskList().get(num - 1).toString() + "\n";
    }

    /**
     * return a statement to show task has been deleted
     * 
     * @param deletedTask
     * @param tasks
     */
    public String deleteTask(Task deletedTask, TaskList tasks) {
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\n" + "Now you have "
                + tasks.getNumberOfTasks() + " tasks in the list.\n";
    }

    /**
     * return the list of tasks
     * 
     * @param tasks
     */
    public String showList(ArrayList<Task> tasks) {
        String result = "Here are the tasks:\n";
        if(tasks.size()>0){
            for (int i = 0; i < tasks.size(); i++) {
                result += i + 1 + "." + tasks.get(i).toString()+"\n";
            }
        } else{
            result = "There are no tasks. Please add some tasks first!\n";
        }
        return result;
    }

    public String endChat() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * print out the error caused by an incomplete instruction
     *
     * @param instruction
     */
    public void instructionError(String instruction) {
        AreaException error = new AreaException(instruction);
        System.out.println(error.toString());
    }

    /**
     * return a statement when run that the file user is looking for does not exist
     * 
     * @throws Exception
     */
    public void showLoadingError() throws Exception {
        System.out.println("Loading Error: Your file does not exist");
    }
}
